package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface UsersServices {
    //fait les verifs (genre pas nul...)
    boolean add(UsersEntity usersEntity);

    boolean update(UsersEntity usersEntity);

    boolean delete(int idUser);

    boolean connexion(UsersEntity usersEntity);

    List<UsersEntity> findAll();

    UsersEntity findById(int id);

    UsersEntity findByOneUsername(String username);

    List<UsersEntity> findByUsername(String username);

    UsersEntity findByUsernameAndPassword(String username, String password);

    UsersEntity findUserWithAddresses(int idUser);


}
