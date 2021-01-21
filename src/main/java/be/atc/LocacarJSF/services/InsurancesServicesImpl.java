package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.InsurancesDAO;
import be.atc.LocacarJSF.dao.InsurancesDAOImpl;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;

import java.util.List;

public class InsurancesServicesImpl implements InsurancesServices {

    InsurancesDAO insurancesDAO = new InsurancesDAOImpl();

    @Override
    public boolean add(InsurancesEntity insurancesEntity) {
        return insurancesDAO.add(insurancesEntity);
    }

    @Override
    public boolean update(InsurancesEntity insurancesEntity) {
        return false;
    }

    @Override
    public boolean delete(InsurancesEntity insurancesEntity) {
        return false;
    }

    @Override
    public List<InsurancesEntity> findAll() {
        return null;
    }

    @Override
    public InsurancesEntity findById(int id) {
        return null;
    }
}
