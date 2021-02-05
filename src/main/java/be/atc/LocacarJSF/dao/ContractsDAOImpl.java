package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class ContractsDAOImpl extends EntityFinderImpl<ContractsEntity> implements ContractsDAO {

    public static Logger log = Logger.getLogger(ContractsDAOImpl.class);

    /**
     * Add entity
     *
     * @param contractsEntity
     * @return
     */
    @Override
    public boolean add(ContractsEntity contractsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(contractsEntity);
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
     * @param contractsEntity
     * @return
     */
    @Override
    public boolean update(ContractsEntity contractsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(contractsEntity);
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

            ContractsEntity c = findById(id);
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
     * Find all entities
     *
     * @return
     */
    @Override
    public List<ContractsEntity> findAll() {
        return this.findByNamedQuery("Contracts.findAll", new ContractsEntity());
    }

    /**
     * find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public ContractsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(ContractsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public ContractsEntity findContractByIdOrdersAndByIdCars(int idOrder, int idCar) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Contracts.findContractByIdOrdersAndByIdCars",
                    ContractsEntity.class)
                    .setParameter("idOrder", idOrder)
                    .setParameter("idCar", idCar)
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
    public List<ContractsEntity> findAllContractsByIdOrder(int idOrder) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Contracts.findAllContractsByIdOrder",
                    ContractsEntity.class)
                    .setParameter("idOrder", idOrder)
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
