package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsGearboxServices {

    public List<CarsGearboxEntity> findAll();

    CarsGearboxEntity findByLabel(String label);
}
