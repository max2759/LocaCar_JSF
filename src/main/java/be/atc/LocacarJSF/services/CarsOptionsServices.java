package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;

import java.util.List;

public interface CarsOptionsServices {

    boolean add(CarsOptionsEntity carsOptionsEntity);

    boolean update(CarsOptionsEntity carsOptionsEntity);

    List<CarsOptionsEntity> findAll();

    CarsOptionsEntity findById(int id);
}
