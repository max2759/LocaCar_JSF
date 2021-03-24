package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface BrandsDAO {

    public boolean add(BrandsEntity brandsEntity);

    public boolean update(BrandsEntity brandsEntity);

    public boolean delete(BrandsEntity brandsEntity);

    public List<BrandsEntity> findAll();

    public BrandsEntity findById(int id);

    public BrandsEntity findByLabel(String label);
}
