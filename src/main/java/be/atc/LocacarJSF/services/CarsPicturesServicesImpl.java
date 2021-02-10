package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsPicturesDAO;
import be.atc.LocacarJSF.dao.CarsPicturesDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;

import java.util.List;

public class CarsPicturesServicesImpl implements CarsPicturesServices {

    CarsPicturesDAO carsPicturesDAO = new CarsPicturesDAOImpl();

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
}
