package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface ContractTypesDAO {

    public boolean add(ContractTypesEntity ContractTypesEntity);

    public boolean update(ContractTypesEntity ContractTypesEntity);

    public List<ContractTypesEntity> findAll();

    public ContractTypesEntity findById(int id);

}
