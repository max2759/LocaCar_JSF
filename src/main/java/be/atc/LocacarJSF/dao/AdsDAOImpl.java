package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class AdsDAOImpl extends EntityFinderImpl<AdsEntity> implements AdsDAO {

    private static final long serialVersionUID = -2193895876065436853L;
    public static Logger log = Logger.getLogger(AdsDAOImpl.class);

    /**
     * Add entity
     *
     * @param adsEntity
     * @return true or false
     */
    @Override
    public boolean add(AdsEntity adsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(adsEntity);
            tx.commit();
            log.info("Persist ok");
            return true;
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Persist echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Update entity
     *
     * @param adsEntity
     * @return true or false
     */
    @Override
    public boolean update(AdsEntity adsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(adsEntity);
            tx.commit();
            log.info("Merge ok");
            return true;
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Merge echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Find all entities
     *
     * @return List of AdsEntity
     */
    @Override
    public List<AdsEntity> findAll() {
        return this.findByNamedQuery("Ads.findAll", new AdsEntity());
    }

    /**
     * find entity by id
     *
     * @param id
     * @return adsEntity
     */
    @Override
    public AdsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(AdsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findByLabel",
                    AdsEntity.class)
                    .setParameter("label", label)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAllDisabledAds() {
        return this.findByNamedQuery("ads.findAllDisabledAds", new AdsEntity());
    }

    @Override
    public List<AdsEntity> findAdsByModels(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByModels",
                    AdsEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByIdUser(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByIdUser",
                    AdsEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findDisabledAdsByUser(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findDisabledAdsByUser",
                    AdsEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByPrice(double price) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByPrice",
                    AdsEntity.class)
                    .setParameter("price", price)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByTypeAds(EnumTypeAds typeAds) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByTypeAds",
                    AdsEntity.class)
                    .setParameter("typeAds", typeAds)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByModelsAndPrice(int idModels, double price) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByModelsAndPrice",
                    AdsEntity.class)
                    .setParameter("idModels", idModels)
                    .setParameter("price", price)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByPriceAndTypeAds(EnumTypeAds enumTypeAds, double price) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByPriceAndTypeAds",
                    AdsEntity.class)
                    .setParameter("enumTypeAds", enumTypeAds)
                    .setParameter("price", price)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByModelAndTypeAds(EnumTypeAds enumTypeAds, int idModel) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByModelAndTypeAds",
                    AdsEntity.class)
                    .setParameter("enumTypeAds", enumTypeAds)
                    .setParameter("idModel", idModel)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<AdsEntity> findAdsByModelsAndPriceAndTypeAds(EnumTypeAds enumTypeAds, int idModel, double price) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("ads.findAdsByModelsAndPriceAndTypeAds",
                    AdsEntity.class)
                    .setParameter("enumTypeAds", enumTypeAds)
                    .setParameter("idModel", idModel)
                    .setParameter("price", price)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }


}
