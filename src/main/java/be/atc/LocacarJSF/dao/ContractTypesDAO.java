package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface ContractTypesDAO {

    boolean add(ContractTypesEntity ContractTypesEntity);

    boolean update(ContractTypesEntity ContractTypesEntity);

    List<ContractTypesEntity> findAll();

    ContractTypesEntity findById(int id);

}
