package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public interface UsersDAO {
    //appels en DB
    boolean add(UsersEntity usersEntity);

    boolean update(UsersEntity usersEntity);

    boolean delete(int idUser);

    List<UsersEntity> findAll();

    UsersEntity findById(int id);

    List<UsersEntity> findByUsername(String username);

    UsersEntity findByOneUsername(String username);

    UsersEntity findByUsernameAndPassword(String username, String password);

    UsersEntity findUserWithAddresses(int idUser);

    //check password

}
