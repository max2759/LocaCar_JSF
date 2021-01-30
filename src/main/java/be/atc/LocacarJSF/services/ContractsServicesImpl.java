package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.ContractsDAO;
import be.atc.LocacarJSF.dao.ContractsDAOImpl;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;

import java.util.List;

public class ContractsServicesImpl implements ContractsServices {

    ContractsDAO contractsDAO = new ContractsDAOImpl();

    /**
     * Add entity
     *
     * @param contractsEntity
     * @return
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
     * @param contractsEntity
     * @return
     */
    @Override
    public boolean update(ContractsEntity contractsEntity) {
        if (contractsEntity != null && findById(contractsEntity.getId()) != null) {
            contractsDAO.update(contractsEntity);
            return true;
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return
     */
    @Override
    public List<ContractsEntity> findAll() {
        return contractsDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public ContractsEntity findById(int id) {
        if (id != 0) {
            return contractsDAO.findById(id);
        }
        return null;
    }
}
