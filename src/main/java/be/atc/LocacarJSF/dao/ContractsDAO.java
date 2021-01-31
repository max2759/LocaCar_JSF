package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.List;

public interface ContractsDAO {

    public boolean add(ContractsEntity contractsEntity);

    public boolean update(ContractsEntity contractsEntity);

    public List<ContractsEntity> findAll();

    public ContractsEntity findById(int id);

    ContractsEntity findContractByIdOrdersAndByIdCars(int idOrder, int idCar);
}
