package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;

public interface UsersServices {
    //fait les verifs (genre pas nul...)
    public boolean add(UsersEntity usersEntity);

    public boolean update(UsersEntity usersEntity);

    public boolean delete(int idUser);

    public boolean connexion(UsersEntity usersEntity);

    public List<UsersEntity> findAll();

    public UsersEntity findById(int id);

    public UsersEntity findByOneUsername(String username);

    public List<UsersEntity> findByUsername(String username);

    public UsersEntity findByUsernameAndPassword(String username, String password);

    public List<UsersEntity> findUserWithAddresses(int idUser);


}
