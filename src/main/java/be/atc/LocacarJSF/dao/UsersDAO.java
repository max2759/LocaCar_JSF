package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.UsersEntity;

import java.util.List;

public interface UsersDAO {
    //appels en DB
    public boolean add(UsersEntity usersEntity);

    public boolean update(UsersEntity usersEntity);

    public boolean delete(int idUser);

    public List<UsersEntity> findAll();

    public UsersEntity findById(int id);

    public List<UsersEntity> findByUsername(String username);

    public UsersEntity findByOneUsername(String username);

    public UsersEntity findByUsernameAndPassword(String username, String password);

    public List<UsersEntity> findUserWithAddresses(int idUser);

    //check password

}
