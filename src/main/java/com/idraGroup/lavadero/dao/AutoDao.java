package com.idraGroup.lavadero.dao;


import com.idraGroup.lavadero.model.Auto;
import com.idragroup.lavadero.dao.CrudDao;
import java.util.Optional;
/**
 * Interface <code>AutoDao</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/dao/AutoDao.java</code>
 * Paquete: <code>com.idraGroup.lavadero.dao</code>
 * </p>
 */


public interface AutoDao extends CrudDao<Auto, Integer>{
    Optional<Auto>findByPatente(String patente);
}
