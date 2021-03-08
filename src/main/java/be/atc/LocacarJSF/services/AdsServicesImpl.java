package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.AdsDAO;
import be.atc.LocacarJSF.dao.AdsDAOImpl;
import be.atc.LocacarJSF.dao.entities.AdsEntity;

import java.util.List;

public class AdsServicesImpl implements AdsServices {

    AdsDAO adsDAO = new AdsDAOImpl();

    /**
     * Add entity
     *
     * @param adsEntity
     * @return
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
     * Find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public AdsEntity findById(int id) {
        if (id != 0) {
            return adsDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<AdsEntity> findByLabel(String label) {
        return adsDAO.findByLabel(label);
    }
}
