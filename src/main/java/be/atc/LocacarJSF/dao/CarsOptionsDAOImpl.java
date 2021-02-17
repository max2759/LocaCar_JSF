package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CarsOptionsDAOImpl implements CarsOptionsDAO {

    public static Logger log = Logger.getLogger(CarsOptionsDAOImpl.class);

    @Override
    public boolean add(CarsOptionsEntity carsOptionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(carsOptionsEntity);
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
    public boolean update(CarsOptionsEntity carsOptionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(carsOptionsEntity);
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
    public List<CarsOptionsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsOptions.findAll",
                    CarsOptionsEntity.class)
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
    public CarsOptionsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(CarsOptionsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

}
