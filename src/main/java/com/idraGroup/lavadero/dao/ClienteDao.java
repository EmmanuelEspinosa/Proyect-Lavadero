package com.idraGroup.lavadero.dao;
import com.idraGroup.lavadero.model.Cliente;
import java.util.Optional;
import com.idragroup.lavadero.dao.CrudDao;

public interface ClienteDao extends CrudDao<Cliente, Integer> {

    Optional<Cliente> findByDni(String dni);
}
