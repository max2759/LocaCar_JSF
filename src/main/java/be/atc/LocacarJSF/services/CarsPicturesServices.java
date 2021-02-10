package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import java.util.List;

public interface CarsPicturesServices {

    public List<CarsPicturesEntity> findAll();

    public CarsPicturesEntity findById(int id);
}
