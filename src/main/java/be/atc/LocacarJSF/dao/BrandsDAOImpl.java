package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class BrandsDAOImpl implements BrandsDAO {

    public static Logger log = Logger.getLogger(InsurancesDAOImpl.class);

    @Override
    public boolean add(BrandsEntity brandsEntity) {

        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(brandsEntity);
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
    public boolean update(BrandsEntity brandsEntity) {
        return false;
    }

    @Override
    public boolean delete(BrandsEntity brandsEntity) {
        return false;
    }

    @Override
    public List<BrandsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("brands.findAll",
                    BrandsEntity.class)
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
    public BrandsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(BrandsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public BrandsEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("brands.findByLabel",
                    BrandsEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
