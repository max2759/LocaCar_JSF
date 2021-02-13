package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.PermissionsEntity;

import java.util.List;

public interface PermissionsServices {
    public boolean add(PermissionsEntity permissionsEntity);

    public boolean update(PermissionsEntity permissionsEntity);

    public boolean delete(PermissionsEntity permissionsEntity);

    public List<PermissionsEntity> findAll();

    public PermissionsEntity findById(int id);


}
