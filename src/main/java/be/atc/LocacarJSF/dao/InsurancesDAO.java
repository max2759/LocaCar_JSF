package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface InsurancesDAO {

    public boolean add(InsurancesEntity insurancesEntity);

    public boolean update(InsurancesEntity insurancesEntity);

    public List<InsurancesEntity> findAll();

    public InsurancesEntity findById(int id);

    public List<InsurancesEntity> findByLabel(String label);
}
