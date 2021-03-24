package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsServices {

    boolean add(CarsEntity carsEntity);

    boolean update(CarsEntity carsEntity);

    List<CarsEntity> findAll();

    CarsEntity findById(int id);
}
