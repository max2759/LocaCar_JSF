package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsGeaboxDAOImpl;
import be.atc.LocacarJSF.dao.CarsGearboxDAO;
import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsGearboxServicesImpl implements CarsGearboxServices {

    CarsGearboxDAO carsGearboxDAO = new CarsGeaboxDAOImpl();

    @Override
    public List<CarsGearboxEntity> findAll() {
        return carsGearboxDAO.findAll();
    }

    @Override
    public CarsGearboxEntity findByLabel(String label) {
        if (label != null) {
            return carsGearboxDAO.findByLabel(label);
        }
        return null;
    }
}
