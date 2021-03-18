package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.ContractTypesDAO;
import be.atc.LocacarJSF.dao.ContractTypesDAOImpl;
import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;

import java.util.List;


/**
 * @author Younes - Arifi
 */
public class ContractTypesServicesImpl implements ContractTypesServices {

    ContractTypesDAO contractTypesDAO = new ContractTypesDAOImpl();

    /**
     * Add entity
     *
     * @param contractTypesEntity ContractTypesEntity
     * @return boolean
     */
    @Override
    public boolean add(ContractTypesEntity contractTypesEntity) {
        if (contractTypesEntity != null) {
            return contractTypesDAO.add(contractTypesEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param contractTypesEntity ContractTypesEntity
     * @return boolean
     */
    @Override
    public boolean update(ContractTypesEntity contractTypesEntity) {
        if (contractTypesEntity != null && findById(contractTypesEntity.getId()) != null) {
            contractTypesDAO.update(contractTypesEntity);
            return true;
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return List<ContractTypesEntity>
     */
    @Override
    public List<ContractTypesEntity> findAll() {
        return contractTypesDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id int
     * @return ContractTypesEntity
     */
    @Override
    public ContractTypesEntity findById(int id) {
        if (id != 0) {
            return contractTypesDAO.findById(id);
        }
        return null;
    }
}
