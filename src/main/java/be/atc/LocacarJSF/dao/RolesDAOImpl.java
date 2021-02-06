package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesEntity;
import org.apache.log4j.Logger;
import utils.EMF;

import javax.persistence.EntityManager;
import java.util.List;

public class RolesDAOImpl implements RolesDAO {

    public static Logger log = Logger.getLogger(RolesDAOImpl.class);

    @Override
    public boolean add(RolesEntity rolesEntity) {
        return false;
    }

    @Override
    public boolean update(RolesEntity rolesEntity) {
        return false;
    }

    @Override
    public boolean delete(RolesEntity rolesEntity) {
        return false;
    }

    @Override
    public List<RolesEntity> findAll() {
        return null;
    }

    @Override
    public RolesEntity findById(int id) {
        EntityManager em = EMF.getEM();
        try {
            return em.find(RolesEntity.class, id);
        } catch (Exception ex) {
            log.info("Nothing");
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    @Override
    public List<RolesEntity> findByLabel(String label) {
        return null;
    }
}
