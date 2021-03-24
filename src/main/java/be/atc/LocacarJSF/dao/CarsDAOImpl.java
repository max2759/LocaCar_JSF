package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsDAOImpl extends EntityFinderImpl<CarsEntity> implements CarsDAO {

    private static final long serialVersionUID = -914690637638574799L;
    public static Logger log = Logger.getLogger(CarsDAOImpl.class);

    /**
     * Add entity
     *
     * @param carsEntity
     * @return
     */
    @Override
    public boolean add(CarsEntity carsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(carsEntity);
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
     * @param carsEntity
     * @return
     */
    @Override
    public boolean update(CarsEntity carsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(carsEntity);
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
     * @return
     */
    @Override
    public List<CarsEntity> findAll() {
        return this.findByNamedQuery("Cars.findAll", new CarsEntity());
    }

    /**
     * find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public CarsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(CarsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<CarsEntity> deleteAdsByCarsID(int idCars) {
        return null;
    }

}
