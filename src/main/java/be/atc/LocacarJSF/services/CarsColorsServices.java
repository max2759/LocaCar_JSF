package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;

import java.util.List;

public interface CarsColorsServices {

    public boolean add(CarsColorsEntity carsColorsEntity);

    public boolean update(CarsColorsEntity carsColorsEntity);

    public List<CarsColorsEntity> findAll();

    public CarsColorsEntity findById(int id);

    public List<CarsColorsEntity> findByLabel(String label);
}
