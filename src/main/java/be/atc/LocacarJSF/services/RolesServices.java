package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.RolesEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface RolesServices {
    //fait les verifs (genre pas nul...)
    boolean add(RolesEntity rolesEntity);

    boolean update(RolesEntity rolesEntity);

    boolean delete(int idRole);

    List<RolesEntity> findAll();

    RolesEntity findById(int id);

    List<RolesEntity> findByLabel(String label);
}
