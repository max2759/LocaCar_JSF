package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;
import utils.EMF;
import utils.EntityFinderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UsersDAOImpl extends EntityFinderImpl<UsersEntity> implements UsersDAO {

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
        return false;
    }

    @Override
    public boolean delete(UsersEntity usersEntity) {
        return false;
    }

    @Override
    public List<UsersEntity> findAll() {
        return null;
    }

    @Override
    public UsersEntity findById(int id) {
        return null;
    }
}
