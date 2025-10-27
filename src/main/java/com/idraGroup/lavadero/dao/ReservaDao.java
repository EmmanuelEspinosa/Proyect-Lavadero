
package com.idraGroup.lavadero.dao;
import com.idraGroup.lavadero.model.Reserva;
import com.idragroup.lavadero.dao.CrudDao;
import java.time.LocalDateTime;
import java.util.Optional;
/**
 * Interface <code>ReservaDao</code> del proyecto Lavadero.
 * <p>
 * Archivo: <code>com/idraGroup/lavadero/dao/ReservaDao.java</code>
 * Paquete: <code>com.idraGroup.lavadero.dao</code>
 * </p>
 */


public interface ReservaDao extends CrudDao<Reserva, Integer> {
    Optional<Reserva> findByTurno(LocalDateTime turno);
}
