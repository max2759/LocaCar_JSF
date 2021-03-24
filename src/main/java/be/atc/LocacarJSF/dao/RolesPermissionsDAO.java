package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface RolesPermissionsDAO {
    boolean add(RolesPermissionsEntity rolesPermissionsEntity);

    boolean update(RolesPermissionsEntity rolesPermissionsEntity);

    boolean delete(int idRolePerm);

    List<RolesPermissionsEntity> findAll();

    RolesPermissionsEntity findById(int id);

    List<RolesPermissionsEntity> findByIDRoles(int idRoles);

    List<RolesPermissionsEntity> findAllForRolesAndPerm(int idRole);

    RolesPermissionsEntity findByRoleAndPerm(int idRole, int idPerm);
}
