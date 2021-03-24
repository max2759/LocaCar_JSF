package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.PermissionsEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface PermissionsServices {
    boolean add(PermissionsEntity permissionsEntity);

    boolean update(PermissionsEntity permissionsEntity);

    boolean delete(PermissionsEntity permissionsEntity);

    List<PermissionsEntity> findAll();

    PermissionsEntity findById(int id);


}
