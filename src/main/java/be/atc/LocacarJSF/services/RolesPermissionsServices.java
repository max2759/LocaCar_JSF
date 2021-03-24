package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface RolesPermissionsServices {
    boolean add(RolesPermissionsEntity rolesPermissionsEntity);

    boolean update(RolesPermissionsEntity rolesPermissionsEntity);

    boolean delete(int idRolePerm);

    List<RolesPermissionsEntity> findAll();

    RolesPermissionsEntity findById(int id);

    List<RolesPermissionsEntity> findByIDRoles(int idRole);

    List<RolesPermissionsEntity> findAllForRolesAndPerm(int idRole);

    RolesPermissionsEntity findByRoleAndPerm(int idRole, int idPerm);
}
