package com.idraGroup.lavadero.dao;
import com.idraGroup.lavadero.model.Cliente;
import java.util.Optional;
import com.idragroup.lavadero.dao.CrudDao;
/**
 * Interface <code>ClienteDao</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/dao/ClienteDao.java</code>
 * Paquete: <code>com.idraGroup.lavadero.dao</code>
 * </p>
 */

public interface ClienteDao extends CrudDao<Cliente, Integer> {

    Optional<Cliente> findByDni(String dni);
}
