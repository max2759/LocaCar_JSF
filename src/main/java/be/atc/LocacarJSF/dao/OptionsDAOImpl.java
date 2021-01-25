package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class OptionsDAOImpl implements OptionsDAO {

    public static Logger log = Logger.getLogger(OptionsDAOImpl.class);

    @Override
    public boolean add(OptionsEntity optionsEntity) {
        return false;
    }

    @Override
    public boolean update(OptionsEntity optionsEntity) {
        return false;
    }

    @Override
    public List<OptionsEntity> findAll() {
        EntityManager em = EMF.getEM();
        try {
            return em.createNamedQuery("Options.findAll",
                    OptionsEntity.class)
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
    public OptionsEntity findById(int id) {
        return null;
    }
}
