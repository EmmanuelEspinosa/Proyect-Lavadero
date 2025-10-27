package com.idraGroup.lavadero.controller;

import com.idraGroup.lavadero.dao.AutoDao;
import com.idraGroup.lavadero.model.Auto;
import java.util.List;
import java.util.Optional;
/**
 * Class <code>AutoController</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/controller/AutoController.java</code>
 * Paquete: <code>com.idraGroup.lavadero.controller</code>
 * </p>
 */

public class AutoController {

    private final AutoDao autoDao;

    public AutoController(AutoDao autoDao) {
        this.autoDao = autoDao;
    }
/**
 * guardarAuto.
 * @param auto Auto.

 * @return Auto.
 */

    public Auto guardarAuto(Auto auto) {
        if (auto.getPatente() == null || auto.getPatente().isBlank()) {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
        if (auto.getTipo() == null || auto.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo de auto no puede estar vacío.");
        }
        return autoDao.create(auto);
    }
/**
 * listarTodos.

 * @return List<Auto>.
 */

    public List<Auto> listarTodos() {
        return autoDao.findAll();
    }
/**
 * buscarPorPatente.
 * @param patente String.

 * @return Optional<Auto>.
 */

    public Optional<Auto> buscarPorPatente(String patente) {
        return autoDao.findByPatente(patente);
    }
/**
 * buscarPorId.
 * @param id Integer.

 * @return Optional<Auto>.
 */

    public Optional<Auto> buscarPorId(Integer id) {
        return autoDao.findById(id);
    }
/**
 * actualizarAuto.
 * @param auto Auto.

 * @return Auto.
 */

    public Auto actualizarAuto(Auto auto) {
        return autoDao.update(auto);
    }
/**
 * eliminarAuto.
 * @param id Integer.

 * @return boolean.
 */

    public boolean eliminarAuto(Integer id) {
        return autoDao.deleteById(id);
    }

}
