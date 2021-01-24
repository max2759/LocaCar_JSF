package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CountriesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public class CountriesDAOImpl implements CountriesDAO {

    public static Logger log = Logger.getLogger(CountriesDAOImpl.class);

    @Override
    public boolean add(CountriesEntity countriesEntity) {
        EntityManager em = EMF.getEM();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(countriesEntity);
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
    public boolean update(CountriesEntity countriesEntity) {
        return false;
    }

    @Override
    public boolean delete(CountriesEntity countriesEntity) {
        return false;
    }

    @Override
    public List<CountriesEntity> findAll() {
        return null;
    }

    @Override
    public CountriesEntity findById(int id) {
        return null;
    }
}
