package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.ContractsDAO;
import be.atc.LocacarJSF.dao.ContractsDAOImpl;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.Collections;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class ContractsServicesImpl implements ContractsServices {

    ContractsDAO contractsDAO = new ContractsDAOImpl();

    /**
     * Add entity
     *
     * @param contractsEntity ContractsEntity
     * @return boolean
     */
    @Override
    public boolean add(ContractsEntity contractsEntity) {
        if (contractsEntity != null) {
            return contractsDAO.add(contractsEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param contractsEntity ContractsEntity
     * @return boolean
     */
    @Override
    public boolean update(ContractsEntity contractsEntity) {
        if (contractsEntity != null && findById(contractsEntity.getId()) != null) {
            contractsDAO.update(contractsEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            return contractsDAO.delete(id);
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return List<ContractsEntity>
     */
    @Override
    public List<ContractsEntity> findAll() {
        return contractsDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id int
     * @return ContractsEntity
     */
    @Override
    public ContractsEntity findById(int id) {
        if (id != 0) {
            return contractsDAO.findById(id);
        }
        return null;
    }

    @Override
    public ContractsEntity findContractByIdOrdersAndByIdCars(int idOrder, int idCar) {
        if ((idOrder != 0) && (idCar != 0)) {
            return contractsDAO.findContractByIdOrdersAndByIdCars(idOrder, idCar);
        }
        return null;
    }

    @Override
    public List<ContractsEntity> findAllContractsByIdOrder(int idOrder) {
        if (idOrder != 0) {
            return contractsDAO.findAllContractsByIdOrder(idOrder);
        }
        return Collections.emptyList();
    }

    @Override
    public Number countContractsByIdOrder(int idOrder) {
        if (idOrder != 0) {
            return contractsDAO.countContractsByIdOrder(idOrder);
        }
        return 0;
    }

    @Override
    public List<ContractsEntity> findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(int idOrder) {
        if (idOrder != 0) {
            return contractsDAO.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(idOrder);
        }
        return Collections.emptyList();
    }

    @Override
    public ContractsEntity findContractByIdCarAndTypeIsLeasing(int idCar) {
        if (idCar != 0) {
            return contractsDAO.findContractByIdCarAndTypeIsLeasing(idCar);
        }
        return null;
    }
}
