package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.ContractInsurancesDAO;
import be.atc.LocacarJSF.dao.ContractInsurancesDAOImpl;
import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;


/**
 * @author Younes - Arifi
 */
public class ContractInsurancesServicesImpl implements ContractInsurancesServices {

    ContractInsurancesDAO contractInsurancesDAO = new ContractInsurancesDAOImpl();

    /**
     * Add entity
     *
     * @param contractInsurancesEntity ContractInsurancesEntity
     * @return boolean
     */
    @Override
    public boolean add(ContractInsurancesEntity contractInsurancesEntity) {
        if (contractInsurancesEntity != null) {
            return contractInsurancesDAO.add(contractInsurancesEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param contractInsurancesEntity ContractInsurancesEntity
     * @return boolean
     */
    @Override
    public boolean update(ContractInsurancesEntity contractInsurancesEntity) {
        if (contractInsurancesEntity != null && findById(contractInsurancesEntity.getId()) != null) {
            contractInsurancesDAO.update(contractInsurancesEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            return contractInsurancesDAO.delete(id);
        }
        return false;
    }

    /**
     * Find entity by id
     *
     * @param id int
     * @return ContractInsurancesEntity
     */
    @Override
    public ContractInsurancesEntity findById(int id) {
        if (id != 0) {
            return contractInsurancesDAO.findById(id);
        }
        return null;
    }

    @Override
    public ContractInsurancesEntity findByIdContract(int idContract) {
        if (idContract != 0) {
            return contractInsurancesDAO.findByIdContract(idContract);
        }
        return null;
    }
}
