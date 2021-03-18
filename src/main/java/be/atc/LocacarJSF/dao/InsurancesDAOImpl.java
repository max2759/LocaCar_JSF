package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class InsurancesDAOImpl extends EntityFinderImpl<InsurancesEntity> implements InsurancesDAO {

    private static final long serialVersionUID = 6906535856992118586L;
    public static Logger log = Logger.getLogger(InsurancesDAOImpl.class);

    /**
     * Add entity
     *
     * @param insurancesEntity InsuranceEntity
     * @return boolean
     */
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

    /**
     * Update entity
     *
     * @param insurancesEntity InsurancesEntity
     * @return boolean
     */
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

    /**
     * Find all entities
     *
     * @return List of InsurancesEntity
     */
    @Override
    public List<InsurancesEntity> findAll() {
        return this.findByNamedQuery("Insurances.findAll", new InsurancesEntity());
    }

    /**
     * find entity by id
     *
     * @param id type int
     * @return InsurancesEntity
     */
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

    /**
     * Find insurances by label
     *
     * @param label String
     * @return List<InsurancesEntity>
     */
    @Override
    public List<InsurancesEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Insurances.findByLabel",
                    InsurancesEntity.class)
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

    /**
     * Find All Active Insurance
     *
     * @return List<InsurancesEntity>
     */
    @Override
    public List<InsurancesEntity> findAllActiveInsurance() {
        return this.findByNamedQuery("Insurances.findAllActiveInsurance", new InsurancesEntity());
    }
}
