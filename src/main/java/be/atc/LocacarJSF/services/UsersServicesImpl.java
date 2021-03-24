package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.UsersDAO;
import be.atc.LocacarJSF.dao.UsersDAOImpl;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;

import java.util.List;
/**
 * @author Larche Marie-Élise
 */
public class UsersServicesImpl implements UsersServices {
    public static Logger log = Logger.getLogger(UsersServicesImpl.class);

    UsersDAO usersDAO = new UsersDAOImpl();

    @Override
    public boolean add(UsersEntity usersEntity) {
        log.info("je suis dans le addUserService");
        if (usersEntity != null) {
            log.info("dans le if de userServ");
            return usersDAO.add(usersEntity);
        }
        return false;
    }

    @Override
    public boolean update(UsersEntity usersEntity) {
        if (usersEntity != null && findById(usersEntity.getId()) != null) {
            usersDAO.update(usersEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int idUser) {
        if (idUser != 0) {
            return usersDAO.delete(idUser);
        }
        return false;
    }

    @Override
    public boolean connexion(UsersEntity usersEntity) {
        return false;
    }

    @Override
    public List<UsersEntity> findAll() {
        log.info("findall iuserServ");
        return usersDAO.findAll();
    }


    @Override
    public UsersEntity findById(int id) {
        if (id != 0) {
            return usersDAO.findById(id);
        }
        return null;
    }

    @Override
    public UsersEntity findByOneUsername(String username) {
        if (username != null) {
            return usersDAO.findByOneUsername(username);
        }
        return null;
    }

    @Override
    public List<UsersEntity> findByUsername(String username) {
        if (username != null) {
            return usersDAO.findByUsername(username);
        }
        return null;
    }

    @Override
    public UsersEntity findByUsernameAndPassword(String username, String password) {
        if (username != null && password != null) {
            return usersDAO.findByUsernameAndPassword(username, password);
        }
        return null;
    }

    @Override
    public UsersEntity findUserWithAddresses(int idUser) {
        log.info("findUserWithAdress in Service");
        if (idUser != 0) {
            return usersDAO.findUserWithAddresses(idUser);
        }
        return null;
    }




}
