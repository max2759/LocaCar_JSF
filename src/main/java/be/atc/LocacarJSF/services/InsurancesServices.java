package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface InsurancesServices {
    boolean add(InsurancesEntity insurancesEntity);

    boolean update(InsurancesEntity insurancesEntity);

    List<InsurancesEntity> findAll();

    InsurancesEntity findById(int id);

    List<InsurancesEntity> findByLabel(String label);

    List<InsurancesEntity> findAllActiveInsurance();
}
