package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsPicturesDAOImpl implements CarsPicturesDAO {

    public static Logger log = Logger.getLogger(CarsPicturesDAO.class);

    @Override
    public boolean add(CarsPicturesEntity carsPicturesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(carsPicturesEntity);
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
    public boolean update(CarsPicturesEntity carsPicturesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(carsPicturesEntity);
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
    public List<CarsPicturesEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findAll",
                    CarsPicturesEntity.class)
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
    public CarsPicturesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findById",
                    CarsPicturesEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Liste vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public CarsPicturesEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findByLabel",
                    CarsPicturesEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Liste vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<CarsPicturesEntity> findCarsPicturesByIdCars(int idCars) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findCarsPicturesByIdCars",
                    CarsPicturesEntity.class)
                    .setParameter("id", idCars)
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
    public CarsPicturesEntity findOnePicturesByIdCars(int idCars) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findOnePicturesByIdCars",
                    CarsPicturesEntity.class)
                    .setParameter("idCars", idCars)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Liste vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
