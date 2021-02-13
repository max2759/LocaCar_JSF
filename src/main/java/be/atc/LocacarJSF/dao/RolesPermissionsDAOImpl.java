package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RolesPermissionsDAOImpl implements RolesPermissionsDAO {

    public static Logger log = Logger.getLogger(RolesPermissionsDAOImpl.class);

    @Override
    public boolean add(RolesPermissionsEntity rolesPermissionsEntity) {
        EntityManager em = EMF.getEM();
        log.info("begin add in DAO");

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(rolesPermissionsEntity);
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
    public boolean update(RolesPermissionsEntity rolesPermissionsEntity) {
        EntityManager em = EMF.getEM();

        log.info("enter in update RolePermDAO");
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(rolesPermissionsEntity);
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
    public boolean delete(RolesPermissionsEntity rolesPermissionsEntity) {
        return false;
    }

    @Override
    public List<RolesPermissionsEntity> findAll() {
        EntityManager em = EMF.getEM();
        List<RolesPermissionsEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            log.info("begin try findall in DAO");
            resultList = em.createNamedQuery("RolesPermissions.findAll",
                    RolesPermissionsEntity.class)
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
    public RolesPermissionsEntity findById(int id) {
        log.info("begin findById in dao");
        EntityManager em = EMF.getEM();
        try {
            log.info("begin try findByOd dao");
            return em.find(RolesPermissionsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<RolesPermissionsEntity> findByIDRoles(int idRoles) {
        return null;
    }
}
