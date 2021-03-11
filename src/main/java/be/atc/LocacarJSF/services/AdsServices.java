package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AdsEntity;

import java.util.List;

public interface AdsServices {
    boolean add(AdsEntity adsEntity);

    boolean update(AdsEntity adsEntity);

    List<AdsEntity> findAll();

    AdsEntity findById(int id);

    List<AdsEntity> findByLabel(String label);

    List<AdsEntity> findAllDisabledAds();

}
