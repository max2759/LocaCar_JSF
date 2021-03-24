package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsOptionsServices {

    boolean add(CarsOptionsEntity carsOptionsEntity);

    boolean update(CarsOptionsEntity carsOptionsEntity);

    boolean delete(int idCars);

    List<CarsOptionsEntity> findAll();

    CarsOptionsEntity findById(int id);

    List<CarsOptionsEntity> findCarsOptionsByCarsId(int idCars);

    CarsOptionsEntity oneCarsOptionsByCarsId(int idCars);

    boolean deleteCarOptionByID(int id);
}
