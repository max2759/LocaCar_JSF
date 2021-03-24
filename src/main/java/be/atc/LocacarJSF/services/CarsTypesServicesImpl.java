package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsTypesDAO;
import be.atc.LocacarJSF.dao.CarsTypesDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsTypesServicesImpl implements CarsTypesServices {

    CarsTypesDAO carsTypesDAO = new CarsTypesDAOImpl();

    @Override
    public List<CarsTypesEntity> findAll() {
        return carsTypesDAO.findAll();
    }

    @Override
    public CarsTypesEntity findByLabel(String label) {
        if (label != null) {
            return carsTypesDAO.findByLabel(label);
        }
        return null;
    }
}
