package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * @author Younes - Arifi
 */
public class OrdersDAOImpl extends EntityFinderImpl<OrdersEntity> implements OrdersDAO {

    private static final long serialVersionUID = -3781319900371047868L;
    public static Logger log = Logger.getLogger(OrdersDAOImpl.class);

    /**
     * Add entity
     *
     * @param OrdersEntity OrdersEntity
     * @return boolean
     */
    @Override
    public boolean add(OrdersEntity OrdersEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(OrdersEntity);
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
     * @param OrdersEntity OrdersEntity
     * @return boolean
     */
    @Override
    public boolean update(OrdersEntity OrdersEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(OrdersEntity);
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
     * @return List<OrdersEntity>
     */
    @Override
    public List<OrdersEntity> findAll() {
        return this.findByNamedQuery("Orders.findAll", new OrdersEntity());
    }

    /**
     * find entity by id
     *
     * @param id int
     * @return OrdersEntity
     */
    @Override
    public OrdersEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(OrdersEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Find OrdersEntity by Id User And Status is Pending
     *
     * @param idUser int
     * @return OrdersEntity
     */
    @Override
    public OrdersEntity findByIdUsersAndStatusIsPending(int idUser) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Orders.findByIdUsersAndStatusIsPending",
                    OrdersEntity.class)
                    .setParameter("idUser", idUser)
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
     * Find all orders by Id User and statusis validate or canceled
     *
     * @param idUser int
     * @return List<OrdersEntity>
     */
    @Override
    public List<OrdersEntity> findAllByIdUsersAndStatusIsValidateOrCanceled(int idUser) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Orders.findAllByIdUsersAndStatusIsValidateOrCanceled",
                    OrdersEntity.class)
                    .setParameter("idUser", idUser)
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
     * Find All orders By Username Users And Status Is Validate Or Canceled
     *
     * @param username String
     * @return List<OrdersEntity>
     */
    @Override
    public List<OrdersEntity> findAllByUsernameUsersAndStatusIsValidateOrCanceled(String username) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Orders.findAllByUsernameUsersAndStatusIsValidateOrCanceled",
                    OrdersEntity.class)
                    .setParameter("username", username)
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
     * Find All Orders By Id Order And Status Is Validate Or Canceled
     *
     * @param idOrder int
     * @return List<OrdersEntity>
     */
    @Override
    public List<OrdersEntity> findAllByIdOrderAndStatusIsValidateOrCanceled(int idOrder) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Orders.findAllByIdOrderAndStatusIsValidateOrCanceled",
                    OrdersEntity.class)
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
     * Find All Orders By Id User And Status Is Validate
     *
     * @param idUser int
     * @return List<OrdersEntity>
     */
    @Override
    public List<OrdersEntity> findAllOrdersByIdUserAndStatusIsValidate(int idUser) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Orders.findAllOrdersByIdUserAndStatusIsValidate",
                    OrdersEntity.class)
                    .setParameter("idUser", idUser)
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
