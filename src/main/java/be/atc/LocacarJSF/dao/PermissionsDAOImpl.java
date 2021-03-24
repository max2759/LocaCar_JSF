package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.PermissionsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class PermissionsDAOImpl implements PermissionsDAO {

    public static Logger log = Logger.getLogger(PermissionsDAOImpl.class);

    @Override
    public boolean add(PermissionsEntity permissionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(permissionsEntity);
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
    public boolean update(PermissionsEntity permissionsEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(permissionsEntity);
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
    public boolean delete(PermissionsEntity permissionsEntity) {
        return false;
    }

    @Override
    public List<PermissionsEntity> findAll() {

        EntityManager em = EMF.getEM();
        List<PermissionsEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
            resultList = em.createNamedQuery("Permissions.findAll",
                    PermissionsEntity.class)
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

/*        List<RolesPermissionsEntity> resultList = null;
        log.info("cvall to findAll DAO");
        try {
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
        return null;

 */
    }

    @Override
    public PermissionsEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(PermissionsEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

}
