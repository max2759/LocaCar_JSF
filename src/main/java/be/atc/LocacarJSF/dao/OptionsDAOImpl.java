package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class OptionsDAOImpl implements OptionsDAO {

    public static Logger log = Logger.getLogger(OptionsDAOImpl.class);

    @Override
    public boolean add(OptionsEntity optionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(optionsEntity);
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
    public boolean update(OptionsEntity optionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(optionsEntity);
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

    @Override
    public List<OptionsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Options.findAll",
                    OptionsEntity.class)
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
    public OptionsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(OptionsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<OptionsEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Options.findByLabel",
                    OptionsEntity.class)
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
}
