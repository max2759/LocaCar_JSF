package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsColorsDAOImpl implements CarsColorsDAO {

    public static Logger log = Logger.getLogger(CarsColorsDAO.class);

    @Override
    public boolean add(CarsColorsEntity carsColorsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(carsColorsEntity);
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
    public boolean update(CarsColorsEntity carsColorsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(carsColorsEntity);
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
    public List<CarsColorsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("CarsColors.findAll",
                    CarsColorsEntity.class)
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
    public CarsColorsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(CarsColorsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public CarsColorsEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("CarsColors.findByLabel",
                    CarsColorsEntity.class)
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
    public List<CarsColorsEntity> findByLabelList(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("CarsColors.findByLabel",
                    CarsColorsEntity.class)
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
