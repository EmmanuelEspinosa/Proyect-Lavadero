package com.idragroup.lavadero.dao;

import com.idragroup.lavadero.dao.mem.DataStore;
import com.idragroup.lavadero.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO temporal en memoria para la entidad Cliente.
 * -------------------------------------------------
 * Esta clase simula el acceso a base de datos usando
 * las listas del DataStore. Se reemplazará por una
 * versión JDBC cuando esté lista la BD.
 */
public class ClienteDao implements CrudDao<Cliente, Integer> {

    @Override
    public Cliente create(Cliente c) {
        // Validaciones básicas
        if (c == null) throw new IllegalArgumentException("Cliente null");
        if (c.getNombre() == null || c.getNombre().isBlank())
            throw new IllegalArgumentException("Nombre requerido");
        if (c.getDni() == null || !c.getDni().matches("\\d{7,8}"))
            throw new IllegalArgumentException("DNI inválido (7-8 dígitos)");

        // Evita duplicar clientes con el mismo DNI
        boolean existeDni = DataStore.CLIENTES.stream()
                .anyMatch(x -> x.getDni().equals(c.getDni()));
        if (existeDni)
            throw new IllegalArgumentException("Ya existe un cliente con ese DNI");

        // Asigna un ID autoincremental
        c.setId(DataStore.SEQ_CLIENTE.getAndIncrement());

        // Guarda el cliente en la lista en memoria
        DataStore.CLIENTES.add(c);
        return c;
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        // Busca cliente por ID
        return DataStore.CLIENTES.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public Optional<Cliente> findByDni(String dni) {
        // Busca cliente por DNI exacto
        return DataStore.CLIENTES.stream()
                .filter(c -> c.getDni().equals(dni))
                .findFirst();
    }

    @Override
    public List<Cliente> findAll() {
        // Devuelve una copia para evitar modificar la lista real
        return new ArrayList<>(DataStore.CLIENTES);
    }

    @Override
    public Cliente update(Cliente c) {
        // Valida que exista antes de actualizar
        if (c == null) throw new IllegalArgumentException("Cliente null");
        var actual = findById(c.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

        // Evita duplicar DNI en otro cliente
        boolean dniTomado = DataStore.CLIENTES.stream()
                .anyMatch(x -> x.getId() != c.getId() && x.getDni().equals(c.getDni()));
        if (dniTomado)
            throw new IllegalArgumentException("DNI ya asignado a otro cliente");

        // Actualiza los campos principales
        actual.setNombre(c.getNombre());
        actual.setDni(c.getDni());
        actual.setTelefono(c.getTelefono());
        return actual;
    }

    @Override
    public boolean deleteById(Integer id) {
        // Elimina cliente por ID
        return DataStore.CLIENTES.removeIf(c -> c.getId() == id);
    }
}
