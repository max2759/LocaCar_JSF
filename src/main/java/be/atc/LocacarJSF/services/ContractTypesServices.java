package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface ContractTypesServices {
    boolean add(ContractTypesEntity contractTypesEntity);

    boolean update(ContractTypesEntity contractTypesEntity);

    List<ContractTypesEntity> findAll();

    ContractTypesEntity findById(int id);

}
