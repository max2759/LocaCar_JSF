package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class ContractsDAOImpl extends EntityFinderImpl<ContractsEntity> implements ContractsDAO {

    private static final long serialVersionUID = -9140171618673528522L;
    public static Logger log = Logger.getLogger(ContractsDAOImpl.class);

    /**
     * Add entity
     *
     * @param contractsEntity ContractsEntity
     * @return boolean
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
     * @param contractsEntity ContractsEntity
     * @return boolean
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
     * @return List<ContractsEntity>
     */
    @Override
    public List<ContractsEntity> findAll() {
        return this.findByNamedQuery("Contracts.findAll", new ContractsEntity());
    }

    /**
     * find entity by id
     *
     * @param id int
     * @return ContractsEntity
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

    /**
     * Find Contract By Id Orders And By Id Cars
     *
     * @param idOrder int
     * @param idCar   int
     * @return ContractsEntity
     */
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

    /**
     * Find All Contracts By Id Order
     *
     * @param idOrder int
     * @return List<ContractsEntity>
     */
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

    /**
     * Count Contracts By Id Order
     *
     * @param idOrder int
     * @return Number
     */
    @Override
    public Number countContractsByIdOrder(int idOrder) {
        EntityManager em = EMF.getEM();
        try {
            return ((Number) em.createNamedQuery("Contracts.countContractsByIdOrder")
                    .setParameter("idOrder", idOrder)
                    .getSingleResult()).intValue();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Find All Contracts By Id Order And Deadline Is Lower Than 1 Month
     *
     * @param idOrder int
     * @return List<ContractsEntity>
     */
    @Override
    public List<ContractsEntity> findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(int idOrder) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Contracts.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month",
                    ContractsEntity.class)
                    .setParameter("idOrder", idOrder)
                    .setParameter("todayPlus1Month", LocalDateTime.now().plusMonths(1))
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
     * Find Contract By Id Car And Type Is Leasing
     *
     * @param idCar int
     * @return ContractsEntity
     */
    @Override
    public ContractsEntity findContractByIdCarAndTypeIsLeasing(int idCar) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Contracts.findContractByIdCarAndTypeIsLeasing",
                    ContractsEntity.class)
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

}
