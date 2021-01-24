package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CountriesDAO;
import be.atc.LocacarJSF.dao.CountriesDAOImpl;
import be.atc.LocacarJSF.dao.entities.CountriesEntity;

import java.util.List;

public class CountriesServicesImpl implements CountriesServices {
    CountriesDAO countriesDAO = new CountriesDAOImpl();

    @Override
    public boolean add(CountriesEntity countriesEntity) {
        return countriesDAO.add(countriesEntity);
    }

    @Override
    public boolean update(CountriesEntity countriesEntity) {
        return false;
    }

    @Override
    public boolean delete(CountriesEntity countriesEntity) {
        return false;
    }

    @Override
    public List<CountriesEntity> findAll() {
        return null;
    }

    @Override
    public CountriesEntity findById(int id) {
        return null;
    }
}
