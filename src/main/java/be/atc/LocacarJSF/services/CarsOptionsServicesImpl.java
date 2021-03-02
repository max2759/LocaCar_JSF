package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsOptionsDAO;
import be.atc.LocacarJSF.dao.CarsOptionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;

import java.util.List;

public class CarsOptionsServicesImpl implements CarsOptionsServices {

    CarsOptionsDAO carsOptionsDAO = new CarsOptionsDAOImpl();

    @Override
    public boolean add(CarsOptionsEntity carsOptionsEntity) {
        if (carsOptionsEntity != null) {
            return carsOptionsDAO.add(carsOptionsEntity);
        }
        return false;
    }

    @Override
    public boolean update(CarsOptionsEntity carsOptionsEntity) {
        if (carsOptionsEntity != null) {
            carsOptionsDAO.update(carsOptionsEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<CarsOptionsEntity> findAll() {
        return carsOptionsDAO.findAll();
    }

    @Override
    public CarsOptionsEntity findById(int id) {
        if (id != 0) {
            return carsOptionsDAO.findById(id);
        }
        return null;
    }
}