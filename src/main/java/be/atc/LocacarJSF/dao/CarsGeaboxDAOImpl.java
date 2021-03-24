package be.atc.LocacarJSF.dao;


import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsGeaboxDAOImpl implements CarsGearboxDAO {

    public static Logger log = Logger.getLogger(CarsGearboxDAO.class);

    @Override
    public List<CarsGearboxEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsGearbox.findAll",
                    CarsGearboxEntity.class)
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
    public CarsGearboxEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carsGearbox.findByLabel",
                    CarsGearboxEntity.class)
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
