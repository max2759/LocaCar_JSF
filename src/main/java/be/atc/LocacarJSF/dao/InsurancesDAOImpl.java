package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class InsurancesDAOImpl implements InsurancesDAO {

    public static Logger log = Logger.getLogger(InsurancesDAOImpl.class);

    @Override
    public boolean add(InsurancesEntity insurancesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(insurancesEntity);
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
    public boolean update(InsurancesEntity insurancesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(insurancesEntity);
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
    public List<InsurancesEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Insurances.findAll",
                    InsurancesEntity.class)
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
    public InsurancesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(InsurancesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
