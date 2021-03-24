package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsGearboxDAO {

    public List<CarsGearboxEntity> findAll();

    public CarsGearboxEntity findByLabel(String label);
}
