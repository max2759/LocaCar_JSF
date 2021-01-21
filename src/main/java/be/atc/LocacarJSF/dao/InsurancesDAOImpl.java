package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class InsurancesDAOImpl implements InsurancesDAO {

    public static Logger log = Logger.getLogger(InsurancesDAOImpl.class);

    @Override
    public boolean add(InsurancesEntity insurancesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(insurancesEntity);
            tx.commit();
            log.info("Persist ok");
            return true;
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();

            log.info("Persist echec");
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public boolean update(InsurancesEntity insurancesEntity) {
        return false;
    }

    @Override
    public boolean delete(InsurancesEntity insurancesEntity) {
        return false;
    }

    @Override
    public List<InsurancesEntity> findAll() {
        return null;
    }

    @Override
    public InsurancesEntity findById(int id) {
        return null;
    }
}
