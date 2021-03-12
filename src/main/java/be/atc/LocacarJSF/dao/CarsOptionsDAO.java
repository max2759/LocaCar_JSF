package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;

import java.util.List;

public interface CarsOptionsDAO {

    public boolean add(CarsOptionsEntity carsOptionsEntity);

    public boolean update(CarsOptionsEntity carsOptionsEntity);

    public List<CarsOptionsEntity> findAll();

    public CarsOptionsEntity findById(int id);

    List<CarsOptionsEntity> findCarsOptionsByCarsId(int idCars);
}
