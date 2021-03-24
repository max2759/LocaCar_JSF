package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsOptionsDAO;
import be.atc.LocacarJSF.dao.CarsOptionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

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
    public boolean delete(int idCars) {
        if (idCars != 0) {
            carsOptionsDAO.delete(idCars);
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

    @Override
    public List<CarsOptionsEntity> findCarsOptionsByCarsId(int idCars) {
        if (idCars != 0) {
            return carsOptionsDAO.findCarsOptionsByCarsId(idCars);
        }
        return null;
    }

    @Override
    public CarsOptionsEntity oneCarsOptionsByCarsId(int idCars) {
        if (idCars != 0) {
            return carsOptionsDAO.oneCarsOptionsByCarsId(idCars);
        }
        return null;
    }

    @Override
    public boolean deleteCarOptionByID(int id) {
        if (id != 0) {
            return carsOptionsDAO.deleteCarOptionByID(id);
        }
        return false;
    }
}
