package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;

public interface UsersDAO {
    //appels en DB
    boolean add(UsersEntity usersEntity);

    boolean update(UsersEntity usersEntity);

    boolean delete(UsersEntity usersEntity);

    List<UsersEntity> findAll();

    UsersEntity findById(int id);

    //check password

}
