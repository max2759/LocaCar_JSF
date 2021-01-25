package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.OptionsDAO;
import be.atc.LocacarJSF.dao.OptionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.OptionsEntity;

import java.util.List;

public class OptionsServicesImpl implements OptionsServices {

    OptionsDAO optionsDAO = new OptionsDAOImpl();

    @Override
    public boolean add(OptionsEntity optionsEntity) {
        return false;
    }

    @Override
    public boolean update(OptionsEntity optionsEntity) {
        return false;
    }

    @Override
    public List<OptionsEntity> findAll() {
        return optionsDAO.findAll();
    }

    @Override
    public OptionsEntity findById(int id) {
        return null;
    }
}
