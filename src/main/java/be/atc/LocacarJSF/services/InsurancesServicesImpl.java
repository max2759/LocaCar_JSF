package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.InsurancesDAO;
import be.atc.LocacarJSF.dao.InsurancesDAOImpl;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.Collections;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class InsurancesServicesImpl implements InsurancesServices {

    InsurancesDAO insurancesDAO = new InsurancesDAOImpl();

    /**
     * Add entity
     *
     * @param insurancesEntity InsurancesEntity
     * @return boolean
     */
    @Override
    public boolean add(InsurancesEntity insurancesEntity) {
        if (insurancesEntity != null) {
            return insurancesDAO.add(insurancesEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param insurancesEntity InsurancesEntity
     * @return boolean
     */
    @Override
    public boolean update(InsurancesEntity insurancesEntity) {
        if (insurancesEntity != null && findById(insurancesEntity.getId()) != null) {
            insurancesDAO.update(insurancesEntity);
            return true;
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return List of InsurancesEntity
     */
    @Override
    public List<InsurancesEntity> findAll() {
        return insurancesDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id type int
     * @return InsurancesEntity
     */
    @Override
    public InsurancesEntity findById(int id) {
        if (id != 0) {
            return insurancesDAO.findById(id);
        }
        return null;
    }

    /**
     * Find entities by label
     *
     * @param label type String
     * @return List of InsurancesEntity
     */
    @Override
    public List<InsurancesEntity> findByLabel(String label) {
        if (label != null) {
            return insurancesDAO.findByLabel(label);
        }
        return Collections.emptyList();
    }

    @Override
    public List<InsurancesEntity> findAllActiveInsurance() {
        return insurancesDAO.findAllActiveInsurance();
    }


}
