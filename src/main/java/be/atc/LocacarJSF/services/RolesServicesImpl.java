package be.atc.LocacarJSF.services;


import be.atc.LocacarJSF.dao.RolesDAO;
import be.atc.LocacarJSF.dao.RolesDAOImpl;
import be.atc.LocacarJSF.dao.entities.RolesEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class RolesServicesImpl implements RolesServices {

    public static Logger log = Logger.getLogger(RolesServicesImpl.class);

    RolesDAO rolesDAO = new RolesDAOImpl();

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
        if (id != 0) {
            return rolesDAO.findById(id);
        }
        return null;
    }
}
