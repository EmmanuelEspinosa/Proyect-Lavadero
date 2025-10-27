
package com.idraGroup.lavadero.dao;
import com.idraGroup.lavadero.model.Reserva;
import com.idragroup.lavadero.dao.CrudDao;
import java.time.LocalDateTime;
import java.util.Optional;


public interface ReservaDao extends CrudDao<Reserva, Integer> {
    Optional<Reserva> findByTurno(LocalDateTime turno);
}
