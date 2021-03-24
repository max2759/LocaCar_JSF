package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.BrandsDAO;
import be.atc.LocacarJSF.dao.BrandsDAOImpl;
import be.atc.LocacarJSF.dao.entities.BrandsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class BrandsServicesImpl implements BrandsServices {

    BrandsDAO brandsDAO = new BrandsDAOImpl();

    @Override
    public boolean add(BrandsEntity brandsEntity) {
        return brandsDAO.add(brandsEntity);
    }

    @Override
    public boolean update(BrandsEntity brandsEntity) {
        return false;
    }

    @Override
    public boolean delete(BrandsEntity brandsEntity) {
        return false;
    }

    @Override
    public List<BrandsEntity> findAll() {
        return brandsDAO.findAll();
    }

    @Override
    public BrandsEntity findById(int id) {
        if (id != 0) {
            return brandsDAO.findById(id);
        }
        return null;
    }

    @Override
    public BrandsEntity findByLabel(String label) {
        if (label != null) {
            return brandsDAO.findByLabel(label);
        }
        return null;
    }


}
