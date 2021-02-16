package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import java.util.List;

public interface CarsPicturesDAO {

    public boolean add(CarsPicturesEntity carsPicturesEntity);

    public boolean update(CarsPicturesEntity carsPicturesEntity);

    public List<CarsPicturesEntity> findAll();

    public CarsPicturesEntity findById(int id);

    public List<CarsPicturesEntity> findByCarsId(int id);
}
