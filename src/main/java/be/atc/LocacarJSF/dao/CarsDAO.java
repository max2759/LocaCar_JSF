package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsEntity;

import java.util.List;

public interface CarsDAO {

    public boolean add(CarsEntity carsEntity);

    public boolean update(CarsEntity carsEntity);

    public List<CarsEntity> findAll();

    public CarsEntity findById(int id);

}
