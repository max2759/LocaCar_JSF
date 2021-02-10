package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class CarsPicturesDAOImpl implements CarsPicturesDAO {

    public static Logger log = Logger.getLogger(CarsPicturesDAO.class);

    @Override
    public List<CarsPicturesEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findAll",
                    CarsPicturesEntity.class)
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
    public CarsPicturesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsPictures.findById",
                    CarsPicturesEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Liste vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
