package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ContractInsurancesDAOImpl implements ContractInsurancesDAO {

    public static Logger log = Logger.getLogger(ContractInsurancesDAOImpl.class);

    /**
     * Add entity
     *
     * @param contractInsurancesEntity
     * @return
     */
    @Override
    public boolean add(ContractInsurancesEntity contractInsurancesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(contractInsurancesEntity);
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
     * @param contractInsurancesEntity
     * @return
     */
    @Override
    public boolean update(ContractInsurancesEntity contractInsurancesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(contractInsurancesEntity);
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
     * Delete entity
     *
     * @param id int
     * @return if ok : True else False
     */
    @Override
    public boolean delete(int id) {
        EntityManager em = EMF.getEM();
        em.getTransaction();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            ContractInsurancesEntity c = findById(id);
            em.remove(em.merge(c));

            tx.commit();
            log.info("Delete ok");
            return true;
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            log.error("Delete Error");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public ContractInsurancesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(ContractInsurancesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
