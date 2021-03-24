package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface CarsDAO {

    public boolean add(CarsEntity carsEntity);

    public boolean update(CarsEntity carsEntity);

    public List<CarsEntity> findAll();

    public CarsEntity findById(int id);

    List<CarsEntity> deleteAdsByCarsID(int idCars);
}
