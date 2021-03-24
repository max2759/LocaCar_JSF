package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsTypesDAO {

    public List<CarsTypesEntity> findAll();

    public CarsTypesEntity findByLabel(String label);
}
