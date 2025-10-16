package com.idragroup.lavadero.dao;

import com.idragroup.lavadero.dao.mem.DataStore;
import com.idragroup.lavadero.model.Auto;
import com.idragroup.lavadero.model.Cliente;
import com.idragroup.lavadero.model.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO temporal en memoria para la entidad Reserva.
 * ------------------------------------------------
 * Simula las reservas del lavadero.
 * Cada reserva está vinculada a un cliente y un auto.
 */
public class ReservaDao implements CrudDao<Reserva, Integer> {

    @Override
    public Reserva create(Reserva r) {
        if (r == null) throw new IllegalArgumentException("Reserva null");

        // Validaciones mínimas
        Cliente c = r.getCliente();
        Auto a = r.getAuto();
        if (c == null || a == null)
            throw new IllegalArgumentException("Cliente y Auto son requeridos");
        if (r.getTurno() == null)
            throw new IllegalArgumentException("Turno requerido");
        if (r.getTipoLavado() == null || r.getTipoLavado().isBlank())
            throw new IllegalArgumentException("Tipo de lavado requerido");
        if (r.getPrecio() < 0)
            throw new IllegalArgumentException("Precio inválido");

        // Evita doble turno para el mismo auto
        boolean duplicado = DataStore.RESERVAS.stream().anyMatch(x ->
                x.getAuto() != null &&
                x.getAuto().getId() == a.getId() &&
                x.getTurno() != null &&
                x.getTurno().equals(r.getTurno())
        );
        if (duplicado)
            throw new IllegalArgumentException("Ya existe una reserva para ese auto en ese turno");

        r.setId(DataStore.SEQ_RESERVA.getAndIncrement());
        DataStore.RESERVAS.add(r);
        return r;
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return DataStore.RESERVAS.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    @Override
    public List<Reserva> findAll() {
        return new ArrayList<>(DataStore.RESERVAS);
    }

    @Override
    public Reserva update(Reserva r) {
        if (r == null) throw new IllegalArgumentException("Reserva null");
        var actual = findById(r.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reserva no existe"));

        // Validaciones de campos
        if (r.getCliente() == null || r.getAuto() == null)
            throw new IllegalArgumentException("Cliente y Auto requeridos");
        if (r.getTurno() == null)
            throw new IllegalArgumentException("Turno requerido");
        if (r.getTipoLavado() == null || r.getTipoLavado().isBlank())
            throw new IllegalArgumentException("Tipo de lavado requerido");
        if (r.getPrecio() < 0)
            throw new IllegalArgumentException("Precio inválido");

        // Evita choques de turno en otro ID
        boolean choque = DataStore.RESERVAS.stream().anyMatch(x ->
                x.getId() != r.getId() &&
                x.getAuto() != null &&
                x.getAuto().getId() == r.getAuto().getId() &&
                x.getTurno() != null &&
                x.getTurno().equals(r.getTurno())
        );
        if (choque)
            throw new IllegalArgumentException("Conflicto de turno para ese auto");

        actual.setCliente(r.getCliente());
        actual.setAuto(r.getAuto());
        actual.setTurno(r.getTurno());
        actual.setTipoLavado(r.getTipoLavado());
        actual.setPrecio(r.getPrecio());
        return actual;
    }

    @Override
    public boolean deleteById(Integer id) {
        return DataStore.RESERVAS.removeIf(r -> r.getId() == id);
    }

    // Métodos extra para búsquedas útiles
    public List<Reserva> findByClienteId(int clienteId) {
        return DataStore.RESERVAS.stream()
                .filter(r -> r.getCliente() != null && r.getCliente().getId() == clienteId)
                .toList();
    }

    public List<Reserva> findByAutoId(int autoId) {
        return DataStore.RESERVAS.stream()
                .filter(r -> r.getAuto() != null && r.getAuto().getId() == autoId)
                .toList();
    }
}
