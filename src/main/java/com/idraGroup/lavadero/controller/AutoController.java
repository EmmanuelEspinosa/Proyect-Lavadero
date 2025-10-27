package com.idraGroup.lavadero.controller;

import com.idraGroup.lavadero.dao.AutoDao;
import com.idraGroup.lavadero.model.Auto;
import java.util.List;
import java.util.Optional;

public class AutoController {

    private final AutoDao autoDao;

    public AutoController(AutoDao autoDao) {
        this.autoDao = autoDao;
    }

    public Auto guardarAuto(Auto auto) {
        if (auto.getPatente() == null || auto.getPatente().isBlank()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
        if (auto.getTipo() == null || auto.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo de auto no puede estar vacío.");
        }
        return autoDao.create(auto);
    }

    public List<Auto> listarTodos() {
        return autoDao.findAll();
    }

    public Optional<Auto> buscarPorPatente(String patente) {
        return autoDao.findByPatente(patente);
    }

    public Optional<Auto> buscarPorId(Integer id) {
        return autoDao.findById(id);
    }

    public Auto actualizarAuto(Auto auto) {
        return autoDao.update(auto);
    }

    public boolean eliminarAuto(Integer id) {
        return autoDao.deleteById(id);
    }

}
