package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractTypesEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class ContractTypesDAOImpl extends EntityFinderImpl<ContractTypesEntity> implements ContractTypesDAO {

    private static final long serialVersionUID = 6858411659195542003L;
    public static Logger log = Logger.getLogger(ContractTypesDAOImpl.class);

    /**
     * Add entity
     *
     * @param ContractTypesEntity ContractTypesEntity
     * @return boolean
     */
    @Override
    public boolean add(ContractTypesEntity ContractTypesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(ContractTypesEntity);
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
     * @param ContractTypesEntity ContractTypesEntity
     * @return boolean
     */
    @Override
    public boolean update(ContractTypesEntity ContractTypesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(ContractTypesEntity);
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
     * @return List<ContractTypesEntity>
     */
    @Override
    public List<ContractTypesEntity> findAll() {
        return this.findByNamedQuery("ContractTypes.findAll", new ContractTypesEntity());
    }

    /**
     * find entity by id
     *
     * @param id int
     * @return ContractTypesEntity
     */
    @Override
    public ContractTypesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(ContractTypesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

}
