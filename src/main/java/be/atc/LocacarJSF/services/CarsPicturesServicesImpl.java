package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsPicturesDAO;
import be.atc.LocacarJSF.dao.CarsPicturesDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import java.util.Collections;
import java.util.List;

public class CarsPicturesServicesImpl implements CarsPicturesServices {

    CarsPicturesDAO carsPicturesDAO = new CarsPicturesDAOImpl();

    @Override
    public boolean add(CarsPicturesEntity carsPicturesEntity) {
        if (carsPicturesEntity != null) {
            return carsPicturesDAO.add(carsPicturesEntity);
        }
        return false;
    }

    @Override
    public boolean update(CarsPicturesEntity carsPicturesEntity) {
        if (carsPicturesEntity != null && findById(carsPicturesEntity.getId()) != null) {
            carsPicturesDAO.update(carsPicturesEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<CarsPicturesEntity> findAll() {
        return carsPicturesDAO.findAll();
    }

    @Override
    public CarsPicturesEntity findById(int id) {
        if (id != 0) {
            return carsPicturesDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<CarsPicturesEntity> findByCarsId(int id) {
        if (id != 0) {
            return carsPicturesDAO.findByCarsId(id);
        }
        return Collections.emptyList();
    }

    @Override
    public CarsPicturesEntity findByLabel(String label) {

        if (label != null) {
            return carsPicturesDAO.findByLabel(label);
        }
        return null;
    }
}
