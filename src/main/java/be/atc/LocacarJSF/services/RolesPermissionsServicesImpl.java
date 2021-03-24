package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.RolesPermissionsDAO;
import be.atc.LocacarJSF.dao.RolesPermissionsDAOImpl;
import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class RolesPermissionsServicesImpl implements RolesPermissionsServices {

    public static Logger log = Logger.getLogger(RolesPermissionsDAOImpl.class);
    RolesPermissionsDAO rolesPermissionsDAO = new RolesPermissionsDAOImpl();

    @Override
    public boolean add(RolesPermissionsEntity rolesPermissionsEntity) {
        log.info("begin add service");
        if (rolesPermissionsEntity != null) {
            return rolesPermissionsDAO.add(rolesPermissionsEntity);
        }
        return false;
    }

    @Override
    public boolean update(RolesPermissionsEntity rolesPermissionsEntity) {
        log.info("begin update service");
        if (rolesPermissionsEntity != null && findById(rolesPermissionsEntity.getId()) != null) {
            return rolesPermissionsDAO.update(rolesPermissionsEntity);

        }
        return false;
    }

    @Override
    public boolean delete(int idRolePerm) {
        log.info("begin delete");
        if (idRolePerm != 0) {
            log.info("in the if: idRolePerm: " + idRolePerm);
            return rolesPermissionsDAO.delete(idRolePerm);
        }
        return false;
    }

    @Override
    public List<RolesPermissionsEntity> findAll() {
        return rolesPermissionsDAO.findAll();
    }

    @Override
    public RolesPermissionsEntity findById(int id) {
        log.info("finById in service");
        if (id != 0) {
            log.info("dans le if du findById service");
            return rolesPermissionsDAO.findById(id);
        }
        return null;
    }

    @Override
    public List<RolesPermissionsEntity> findByIDRoles(int idRoles) {
        log.info("reolPerm id role service ");
        if (idRoles != 0) {
            return rolesPermissionsDAO.findByIDRoles(idRoles);
        }
        return null;
    }

    @Override
    public List<RolesPermissionsEntity> findAllForRolesAndPerm(int idRole) {
        if (idRole != 0) {
            return rolesPermissionsDAO.findAllForRolesAndPerm(idRole);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public RolesPermissionsEntity findByRoleAndPerm(int idRole, int idPerm) {
        if (idRole != 0 && idPerm != 0) {
            return rolesPermissionsDAO.findByRoleAndPerm(idRole, idPerm);
        }
        return null;
    }


}
