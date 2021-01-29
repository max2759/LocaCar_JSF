package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.List;

public interface InsurancesServices {
    boolean add(InsurancesEntity insurancesEntity);

    boolean update(InsurancesEntity insurancesEntity);
/*
    boolean delete(int id);*/

    List<InsurancesEntity> findAll();

    InsurancesEntity findById(int id);

    List<InsurancesEntity> findByLabel(String label);
}
