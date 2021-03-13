package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;

import java.util.List;

public interface RolesPermissionsDAO {
    public boolean add(RolesPermissionsEntity rolesPermissionsEntity);

    public boolean update(RolesPermissionsEntity rolesPermissionsEntity);

    public boolean delete(int idRolePerm);

    public List<RolesPermissionsEntity> findAll();

    public RolesPermissionsEntity findById(int id);

    public List<RolesPermissionsEntity> findByIDRoles(int idRoles);
}
