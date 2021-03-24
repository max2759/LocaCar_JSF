package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsFuelsDAO;
import be.atc.LocacarJSF.dao.CarsFuelsDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsFuelsServicesImpl implements CarsFuelsServices {

    CarsFuelsDAO carsFuelsDAO = new CarsFuelsDAOImpl();

    @Override
    public List<CarsFuelsEntity> findAll() {
        return carsFuelsDAO.findAll();
    }

    @Override
    public CarsFuelsEntity findByLabel(String label) {
        if (label != null) {
            return carsFuelsDAO.findByLabel(label);
        }
        return null;
    }
}
