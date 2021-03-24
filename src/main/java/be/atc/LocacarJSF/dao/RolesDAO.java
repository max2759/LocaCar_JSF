package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.RolesEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface RolesDAO {
    //appels en DB
    boolean add(RolesEntity rolesEntity);

    boolean update(RolesEntity rolesEntity);

    boolean delete(int idRole);

    List<RolesEntity> findAll();

    RolesEntity findById(int id);

    List<RolesEntity> findByLabel(String label);
}
