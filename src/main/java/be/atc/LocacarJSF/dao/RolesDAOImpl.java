package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.RolesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class RolesDAOImpl implements RolesDAO {

    public static Logger log = Logger.getLogger(RolesDAOImpl.class);

    @Override
    public boolean add(RolesEntity rolesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(rolesEntity);
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
    public boolean update(RolesEntity rolesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(rolesEntity);
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
    public boolean delete(int idRole) {
        EntityManager em = EMF.getEM();
        em.getTransaction();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            RolesEntity u = findById(idRole);
            em.remove(em.merge(u));

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

    @Override
    public List<RolesEntity> findAll() {
        EntityManager em = EMF.getEM();
        List<RolesEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            resultList = em.createNamedQuery("Roles.findAll",
                    RolesEntity.class)
                    .getResultList();
            if (resultList != null) {
                log.info(resultList.size() + " results found.");
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            em.clear();
            em.close();
        }
        return resultList;
    }

    @Override
    public RolesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(RolesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<RolesEntity> findByLabel(String label) {
        EntityManager em = EMF.getEM();

        log.info("role in DAO : " + label);

        try {
            return em.createNamedQuery("Roles.findByLabel",
                    RolesEntity.class)
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




}
