package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsFuelsDAO {

    public List<CarsFuelsEntity> findAll();

    public CarsFuelsEntity findByLabel(String label);
}
