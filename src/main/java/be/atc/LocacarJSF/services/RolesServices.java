package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.RolesEntity;

import java.util.List;

public interface RolesServices {
    //fait les verifs (genre pas nul...)
    public boolean add(RolesEntity rolesEntity);

    public boolean update(RolesEntity rolesEntity);

    public boolean delete(int idRole);

    public List<RolesEntity> findAll();

    public RolesEntity findById(int id);

    public List<RolesEntity> findByLabel(String label);
}
