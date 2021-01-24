package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.InsurancesDAO;
import be.atc.LocacarJSF.dao.InsurancesDAOImpl;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.List;

public class InsurancesServicesImpl implements InsurancesServices {

    InsurancesDAO insurancesDAO = new InsurancesDAOImpl();

    @Override
    public boolean add(InsurancesEntity insurancesEntity) {
        if (insurancesEntity != null) {
            return insurancesDAO.add(insurancesEntity);
        }
        return false;
    }

    @Override
    public boolean update(InsurancesEntity insurancesEntity) {
        if (insurancesEntity != null && findById(insurancesEntity.getId()) != null) {
            insurancesDAO.update(insurancesEntity);
            return true;
        }
        return false;
    }
/*
    @Override
    public boolean delete(int id) {
        if (id != 0) {
            InsurancesEntity insurancesEntity = findById(id);
            if (insurancesEntity != null) {
                insurancesDAO.delete(insurancesEntity);
                return true;
            }
        }
        return false;
    }*/

    @Override
    public List<InsurancesEntity> findAll() {
        return null;
    }

    @Override
    public InsurancesEntity findById(int id) {
        if (id != 0) {
            return insurancesDAO.findById(id);
        }
        return null;
    }
}
