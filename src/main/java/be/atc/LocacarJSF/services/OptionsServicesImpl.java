package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.OptionsDAO;
import be.atc.LocacarJSF.dao.OptionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.OptionsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class OptionsServicesImpl implements OptionsServices {

    OptionsDAO optionsDAO = new OptionsDAOImpl();

    @Override
    public boolean add(OptionsEntity optionsEntity) {
        if (optionsEntity != null) {
            return optionsDAO.add(optionsEntity);
        }
        return false;
    }

    @Override
    public boolean update(OptionsEntity optionsEntity) {
        if (optionsEntity != null && findById(optionsEntity.getId()) != null) {
            optionsDAO.update(optionsEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<OptionsEntity> findAll() {
        return optionsDAO.findAll();
    }

    @Override
    public OptionsEntity findById(int id) {
        if (id != 0) {
            return optionsDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<OptionsEntity> findByLabel(String label) {
        if (label != null) {
            return optionsDAO.findByLabel(label);
        }
        return null;
    }
}
