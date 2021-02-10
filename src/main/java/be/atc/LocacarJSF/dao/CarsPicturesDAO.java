package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import java.util.List;

public interface CarsPicturesDAO {

    public List<CarsPicturesEntity> findAll();

    public CarsPicturesEntity findById(int id);
}
