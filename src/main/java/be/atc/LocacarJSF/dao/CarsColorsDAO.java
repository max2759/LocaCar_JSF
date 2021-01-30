package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;

import java.util.List;

public interface CarsColorsDAO {

    public boolean add(CarsColorsEntity carsColorsEntity);

    public boolean update(CarsColorsEntity carsColorsEntity);

    public List<CarsColorsEntity> findAll();

    public CarsColorsEntity findById(int id);

    public List<CarsColorsEntity> findByLabel(String label);
}
