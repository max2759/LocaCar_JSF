package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;

import java.util.List;

public interface RolesPermissionsServices {
    public boolean add(RolesPermissionsEntity rolesPermissionsEntity);

    public boolean update(RolesPermissionsEntity rolesPermissionsEntity);

    public boolean delete(RolesPermissionsEntity rolesPermissionsEntity);

    public List<RolesPermissionsEntity> findAll();

    public RolesPermissionsEntity findById(int id);

    public List<RolesPermissionsEntity> findByIDRoles(int idRole);
}
