package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CountriesEntity;

import java.util.List;

public interface CountriesServices {

    boolean add(CountriesEntity countriesEntity);

    boolean update(CountriesEntity countriesEntity);

    boolean delete(CountriesEntity countriesEntity);

    List<CountriesEntity> findAll();

    CountriesEntity findById(int id);

}
