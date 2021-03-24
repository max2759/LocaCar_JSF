package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.CarsDAO;
import be.atc.LocacarJSF.dao.CarsDAOImpl;
import be.atc.LocacarJSF.dao.entities.CarsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsServicesImpl implements CarsServices {

    CarsDAO carsDAO = new CarsDAOImpl();

    /**
     * Add entity
     *
     * @param carsEntity
     * @return
     */
    @Override
    public boolean add(CarsEntity carsEntity) {
        if (carsEntity != null) {
            return carsDAO.add(carsEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param carsEntity
     * @return
     */
    @Override
    public boolean update(CarsEntity carsEntity) {
        if (carsEntity != null && findById(carsEntity.getId()) != null) {
            carsDAO.update(carsEntity);
            return true;
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return
     */
    @Override
    public List<CarsEntity> findAll() {
        return carsDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public CarsEntity findById(int id) {
        if (id != 0) {
            return carsDAO.findById(id);
        }
        return null;
    }
}
