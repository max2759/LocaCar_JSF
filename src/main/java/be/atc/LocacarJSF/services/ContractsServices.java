package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.List;

public interface ContractsServices {
    boolean add(ContractsEntity contractsEntity);

    boolean update(ContractsEntity contractsEntity);

    List<ContractsEntity> findAll();

    ContractsEntity findById(int id);

}
