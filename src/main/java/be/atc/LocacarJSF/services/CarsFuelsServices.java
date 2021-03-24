package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsFuelsServices {

    public List<CarsFuelsEntity> findAll();

    public CarsFuelsEntity findByLabel(String label);

}
