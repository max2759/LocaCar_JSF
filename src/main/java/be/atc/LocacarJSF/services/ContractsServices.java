package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface ContractsServices {
    boolean add(ContractsEntity contractsEntity);

    boolean update(ContractsEntity contractsEntity);

    boolean delete(int id);

    List<ContractsEntity> findAll();

    ContractsEntity findById(int id);

    ContractsEntity findContractByIdOrdersAndByIdCars(int idOrder, int idCar);

    List<ContractsEntity> findAllContractsByIdOrder(int idOrder);

    Number countContractsByIdOrder(int idOrder);

    List<ContractsEntity> findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(int idOrder);

    ContractsEntity findContractByIdCarAndTypeIsLeasing(int idCar);
}
