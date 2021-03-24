package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Zabbara - Maximilien
 */

public class CarsTypesDAOImpl implements CarsTypesDAO {

    public static Logger log = Logger.getLogger(CarsTypesDAOImpl.class);

    @Override
    public List<CarsTypesEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carTypes.findAll",
                    CarsTypesEntity.class)
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
    public CarsTypesEntity findByLabel(String label) {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("carTypes.findByLabel",
                    CarsTypesEntity.class)
                    .setParameter("label", label)
                    .getSingleResult();
        } catch (Exception ex) {
            log.info("Entité vide");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }
}
