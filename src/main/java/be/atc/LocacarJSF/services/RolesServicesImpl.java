package be.atc.LocacarJSF.services;


import be.atc.LocacarJSF.dao.RolesDAO;
import be.atc.LocacarJSF.dao.RolesDAOImpl;
import be.atc.LocacarJSF.dao.entities.RolesEntity;
import org.apache.log4j.Logger;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class RolesServicesImpl implements RolesServices {

    public static Logger log = Logger.getLogger(RolesServicesImpl.class);

    RolesDAO rolesDAO = new RolesDAOImpl();

    @Override
    public boolean add(RolesEntity rolesEntity) {
        if (rolesEntity != null) {
            return rolesDAO.add(rolesEntity);
        }
        return false;
    }

    @Override
    public boolean update(RolesEntity rolesEntity) {
        if (rolesEntity != null && findById(rolesEntity.getId()) != null) {
            return rolesDAO.update(rolesEntity);

        }
        return false;
    }

    @Override
    public boolean delete(int idRole) {
        log.info("begin delete");
        if (idRole != 0) {
            log.info("in the if: idRolePerm: " + idRole);
            return rolesDAO.delete(idRole);
        }
        return false;
    }

    @Override
    public List<RolesEntity> findAll() {

        log.info("findall iuserServ");
        return rolesDAO.findAll();
    }

    @Override
    public RolesEntity findById(int id) {
        if (id != 0) {
            return rolesDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<RolesEntity> findByLabel(String label) {
        if (label != null) {
            return rolesDAO.findByLabel(label);
        }
        return null;
    }
}
