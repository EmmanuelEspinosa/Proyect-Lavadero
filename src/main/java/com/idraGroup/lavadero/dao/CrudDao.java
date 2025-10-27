package com.idragroup.lavadero.dao;

import java.util.List;
import java.util.Optional;
/**
 * Interface <code>CrudDao</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/dao/CrudDao.java</code>
 * Paquete: <code>com.idragroup.lavadero.dao</code>
 * </p>
 */

public interface CrudDao<T, ID> {
    T create(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    T update(T entity);
    boolean deleteById(ID id);
}
