package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ModelsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class ModelsDAOImpl implements ModelsDAO {

    public static Logger log = Logger.getLogger(ModelsDAOImpl.class);

    @Override
    public boolean add(ModelsEntity modelsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(modelsEntity);
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

    @Override
    public boolean update(ModelsEntity modelsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(modelsEntity);
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

    @Override
    public List<ModelsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Models.findAll",
                    ModelsEntity.class)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Liste vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public ModelsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(ModelsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<ModelsEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Models.findByLabel",
                    ModelsEntity.class)
                    .setParameter("label", label)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public ModelsEntity findByLabelEntity(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Models.findByLabel",
                    ModelsEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<ModelsEntity> findModelsByBrandsId(int brandsID) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Models.findModelsByBrands",
                    ModelsEntity.class)
                    .setParameter("idBrands", brandsID)
                    .getResultList();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
