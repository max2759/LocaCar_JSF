package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.AdsEntity;

import java.util.List;

public interface AdsDAO {

    public boolean add(AdsEntity adsEntity);

    public boolean update(AdsEntity adsEntity);

    public List<AdsEntity> findAll();

    public AdsEntity findById(int id);

}