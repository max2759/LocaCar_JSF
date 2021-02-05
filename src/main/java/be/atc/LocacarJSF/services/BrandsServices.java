package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;

import java.util.List;

public interface BrandsServices {

    public boolean add(BrandsEntity brandsEntity);

    public boolean update(BrandsEntity brandsEntity);

    public boolean delete(BrandsEntity brandsEntity);

    public List<BrandsEntity> findAll();

    public BrandsEntity findById(int id);

    public BrandsEntity findByLabel(String label);
}
