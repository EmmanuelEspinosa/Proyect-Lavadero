package com.idragroup.lavadero.dao;

import com.idragroup.lavadero.dao.mem.DataStore;
import com.idragroup.lavadero.model.Auto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO temporal en memoria para la entidad Auto.
 * ----------------------------------------------
 * Simula la persistencia usando DataStore.
 * Cada auto se identifica por su patente (única).
 */
public class AutoDao implements CrudDao<Auto, Integer> {

    @Override
    public Auto create(Auto a) {
        if (a == null) throw new IllegalArgumentException("Auto null");
        if (a.getPatente() == null || a.getPatente().isBlank())
            throw new IllegalArgumentException("Patente requerida");

        // Normaliza la patente (mayúsculas y sin espacios)
        String pat = a.getPatente().trim().toUpperCase();

        // Evita duplicados por patente
        boolean existe = DataStore.AUTOS.stream()
                .anyMatch(x -> pat.equals(x.getPatente().trim().toUpperCase()));
        if (existe)
            throw new IllegalArgumentException("Ya existe un auto con esa patente");

        // Asigna ID autoincremental y guarda
        a.setId(DataStore.SEQ_AUTO.getAndIncrement());
        a.setPatente(pat);
        DataStore.AUTOS.add(a);
        return a;
    }

    @Override
    public Optional<Auto> findById(Integer id) {
        return DataStore.AUTOS.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public Optional<Auto> findByPatente(String patente) {
        String pat = patente.trim().toUpperCase();
        return DataStore.AUTOS.stream()
                .filter(a -> pat.equals(a.getPatente().trim().toUpperCase()))
                .findFirst();
    }

    @Override
    public List<Auto> findAll() {
        return new ArrayList<>(DataStore.AUTOS);
    }

    @Override
    public Auto update(Auto a) {
        if (a == null) throw new IllegalArgumentException("Auto null");
        var actual = findById(a.getId())
                .orElseThrow(() -> new IllegalArgumentException("Auto no existe"));

        String pat = a.getPatente().trim().toUpperCase();

        // Evita patentes repetidas en distintos autos
        boolean tomado = DataStore.AUTOS.stream()
                .anyMatch(x -> x.getId() != a.getId() && pat.equals(x.getPatente().trim().toUpperCase()));
        if (tomado)
            throw new IllegalArgumentException("Patente ya usada por otro auto");

        actual.setPatente(pat);
        actual.setTipo(a.getTipo());
        return actual;
    }

    @Override
    public boolean deleteById(Integer id) {
        return DataStore.AUTOS.removeIf(a -> a.getId() == id);
    }
}
