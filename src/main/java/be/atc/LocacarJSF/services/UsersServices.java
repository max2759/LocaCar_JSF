package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;

public interface UsersServices {
    //fait les verifs (genre pas nul...)
    public boolean add(UsersEntity usersEntity);

    public boolean update(UsersEntity usersEntity);

    public boolean delete(UsersEntity usersEntity);

    public boolean connexion(UsersEntity usersEntit);

    public List<UsersEntity> findAll();

    public UsersEntity findById(int id);

}
