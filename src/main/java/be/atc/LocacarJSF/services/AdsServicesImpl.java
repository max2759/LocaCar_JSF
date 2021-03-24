package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.AdsDAO;
import be.atc.LocacarJSF.dao.AdsDAOImpl;
import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;

import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class AdsServicesImpl implements AdsServices {

    AdsDAO adsDAO = new AdsDAOImpl();

    /**
     * Add entity
     *
     * @param adsEntity
     * @return true or false
     */
    @Override
    public boolean add(AdsEntity adsEntity) {
        if (adsEntity != null) {
            return adsDAO.add(adsEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param adsEntity
     * @return
     */
    @Override
    public boolean update(AdsEntity adsEntity) {
        if (adsEntity != null && findById(adsEntity.getId()) != null) {
            adsDAO.update(adsEntity);
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
    public List<AdsEntity> findAll() {
        return adsDAO.findAll();
    }

    /**
     * Find ads by id
     *
     * @param id
     * @return ads by id
     */
    @Override
    public AdsEntity findById(int id) {
        if (id != 0) {
            return adsDAO.findById(id);
        }
        return null;
    }

    /**
     * Find ads by label
     *
     * @param label
     * @return list of ads by label
     */
    @Override
    public List<AdsEntity> findByLabel(String label) {
        return adsDAO.findByLabel(label);
    }

    /**
     * Find disabled ads and cars
     *
     * @return List of Ads
     */
    @Override
    public List<AdsEntity> findAllDisabledAds() {
        return adsDAO.findAllDisabledAds();
    }

    @Override
    public List<AdsEntity> findAdsByModels(int id) {
        if (id != 0) {
            return adsDAO.findAdsByModels(id);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByIdUser(int id) {
        if (id != 0) {
            return adsDAO.findAdsByIdUser(id);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findDisabledAdsByUser(int id) {
        if (id != 0) {
            return adsDAO.findDisabledAdsByUser(id);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByPrice(double price) {
        if (price != 0) {
            return adsDAO.findAdsByPrice(price);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByTypeAds(EnumTypeAds typeAds) {
        if (typeAds != null) {
            return adsDAO.findAdsByTypeAds(typeAds);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByModelsAndPrice(int idModels, double price) {
        if (idModels != 0 && price != 0) {
            return adsDAO.findAdsByModelsAndPrice(idModels, price);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByPriceAndTypeAds(EnumTypeAds enumTypeAds, double price) {
        if (enumTypeAds != null && price != 0) {
            return adsDAO.findAdsByPriceAndTypeAds(enumTypeAds, price);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByModelAndTypeAds(EnumTypeAds enumTypeAds, int idModel) {
        if (enumTypeAds != null && idModel != 0) {
            return adsDAO.findAdsByModelAndTypeAds(enumTypeAds, idModel);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findAdsByModelsAndPriceAndTypeAds(EnumTypeAds enumTypeAds, int idModel, double price) {
        if (enumTypeAds != null && idModel != 0 && price != 0) {
            return adsDAO.findAdsByModelsAndPriceAndTypeAds(enumTypeAds, idModel, price);
        }
        return null;
    }
}
