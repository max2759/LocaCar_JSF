package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsColorsDAO;
import be.atc.LocacarJSF.dao.CarsColorsDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsColorsServicesImpl implements CarsColorsServices {

    CarsColorsDAO carsColorsDAO = new CarsColorsDAOImpl();

    @Override
    public boolean add(CarsColorsEntity carsColorsEntity) {
        if (carsColorsEntity != null) {
            return carsColorsDAO.add(carsColorsEntity);
        }
        return false;
    }

    @Override
    public boolean update(CarsColorsEntity carsColorsEntity) {
        if (carsColorsEntity != null && findById(carsColorsEntity.getId()) != null) {
            carsColorsDAO.update(carsColorsEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<CarsColorsEntity> findAll() {
        return carsColorsDAO.findAll();
    }

    @Override
    public CarsColorsEntity findById(int id) {
        if (id != 0) {
            return carsColorsDAO.findById(id);
        }
        return null;
    }

    @Override
    public CarsColorsEntity findByLabel(String label) {
        if (label != null) {
            return carsColorsDAO.findByLabel(label);
        }
        return null;
    }

    @Override
    public List<CarsColorsEntity> findByLabelList(String label) {
        if (label != null) {
            return carsColorsDAO.findByLabelList(label);
        }
        return null;
    }
}
