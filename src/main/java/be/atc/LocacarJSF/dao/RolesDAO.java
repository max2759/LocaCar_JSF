package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesEntity;

import java.util.List;

public interface RolesDAO {
    //appels en DB
    public boolean add(RolesEntity rolesEntity);

    public boolean update(RolesEntity rolesEntity);

    public boolean delete(RolesEntity rolesEntity);

    public List<RolesEntity> findAll();

    public RolesEntity findById(int id);

    public List<RolesEntity> findByLabel(String label);
}
