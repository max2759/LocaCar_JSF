package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.List;

public interface ContractsDAO {

    boolean add(ContractsEntity contractsEntity);

    boolean update(ContractsEntity contractsEntity);

    boolean delete(int id);

    List<ContractsEntity> findAll();

    ContractsEntity findById(int id);

    ContractsEntity findContractByIdOrdersAndByIdCars(int idOrder, int idCar);

    List<ContractsEntity> findAllContractsByIdOrder(int idOrder);
}
