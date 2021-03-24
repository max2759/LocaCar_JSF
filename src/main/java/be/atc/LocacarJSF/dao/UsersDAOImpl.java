package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author Larche Marie-Élise
 */
public class UsersDAOImpl implements UsersDAO {

    public static Logger log = Logger.getLogger(UsersDAOImpl.class);

    @Override
    public boolean add(UsersEntity usersEntity) {
        log.info("begin userDAO");
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            log.info("begin try");
            tx = em.getTransaction();
            tx.begin();
            em.merge(usersEntity);
            tx.commit();
            log.info("Merge ok");
            return true;
        } catch (Exception ex) {
            log.info("egin catch");
            if (tx != null && tx.isActive()) tx.rollback();
            log.info("Merge echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public boolean update(UsersEntity usersEntity) {
        log.info("begin update DAO");
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(usersEntity);
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
    public boolean delete(int idUser) {
        EntityManager em = EMF.getEM();
        em.getTransaction();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            UsersEntity u = findById(idUser);
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
    public List<UsersEntity> findAll() {
        log.info("begin findAllUser");
        EntityManager em = EMF.getEM();
        List<UsersEntity> resultList = null;
        try {
            resultList = em.createNamedQuery("Users.findAll",
                    UsersEntity.class)
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
    public UsersEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(UsersEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    public List<UsersEntity> findByUsername(String username) {
        EntityManager em = EMF.getEM();

        log.info("username in DAO : " + username);

        try {
            return em.createNamedQuery("Users.findByUsername",
                    UsersEntity.class)
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

    @Override
    public UsersEntity findByOneUsername(String username) {
        EntityManager em = EMF.getEM();

        log.info("username in DAO : " + username);

        try {
            return em.createNamedQuery("Users.findByOneUsername",
                    UsersEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    public UsersEntity findByUsernameAndPassword(String username, String password) {
        EntityManager em = EMF.getEM();

        try {
            return em.createNamedQuery("Users.connexion",
                    UsersEntity.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    public UsersEntity findUserWithAddresses(int idUser) {
        log.info("begin findUserWithAdress ine DAO");
        EntityManager em = EMF.getEM();

        log.info("id in DAO : " + idUser);

        try {
            UsersEntity list = em.createNamedQuery("Users.findUserWithAddresses",
                    UsersEntity.class)
                    .setParameter("idUser", idUser)
                    .getSingleResult();
            return list;
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

}
