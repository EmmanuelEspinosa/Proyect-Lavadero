package com.idraGroup.lavadero.dao;


import com.idraGroup.lavadero.model.Auto;
import com.idragroup.lavadero.dao.CrudDao;
import java.util.Optional;


public interface AutoDao extends CrudDao<Auto, Integer>{
    Optional<Auto>findByPatente(String patente);
}
