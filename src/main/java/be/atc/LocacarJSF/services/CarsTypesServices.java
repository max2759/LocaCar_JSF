package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsTypesServices {

    public List<CarsTypesEntity> findAll();

    public CarsTypesEntity findByLabel(String label);
}
