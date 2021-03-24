package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public interface AdsServices {
    boolean add(AdsEntity adsEntity);

    boolean update(AdsEntity adsEntity);

    List<AdsEntity> findAll();

    AdsEntity findById(int id);

    List<AdsEntity> findByLabel(String label);

    List<AdsEntity> findAllDisabledAds();

    List<AdsEntity> findAdsByModels(int id);

    List<AdsEntity> findAdsByIdUser(int id);

    List<AdsEntity> findDisabledAdsByUser(int id);

    List<AdsEntity> findAdsByPrice(double price);

    List<AdsEntity> findAdsByTypeAds(EnumTypeAds typeAds);

    List<AdsEntity> findAdsByModelsAndPrice(int idModels, double price);

    List<AdsEntity> findAdsByPriceAndTypeAds(EnumTypeAds enumTypeAds, double price);

    List<AdsEntity> findAdsByModelAndTypeAds(EnumTypeAds enumTypeAds, int idModel);

    List<AdsEntity> findAdsByModelsAndPriceAndTypeAds(EnumTypeAds enumTypeAds, int idModel, double price);
}
