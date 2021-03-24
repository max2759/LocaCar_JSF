package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.PermissionsEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface PermissionsDAO {
    boolean add(PermissionsEntity permissionsEntity);

    boolean update(PermissionsEntity permissionsEntity);

    boolean delete(PermissionsEntity permissionsEntity);

    List<PermissionsEntity> findAll();

    PermissionsEntity findById(int id);

}
