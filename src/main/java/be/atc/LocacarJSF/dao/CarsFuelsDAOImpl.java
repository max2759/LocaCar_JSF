package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsFuelsDAOImpl implements CarsFuelsDAO {

    public static Logger log = Logger.getLogger(CarsFuelsDAO.class);

    @Override
    public List<CarsFuelsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsFuels.findAll",
                    CarsFuelsEntity.class)
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
    public CarsFuelsEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsFuels.findByLabel",
                    CarsFuelsEntity.class)
                    .setParameter("label", label)
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
