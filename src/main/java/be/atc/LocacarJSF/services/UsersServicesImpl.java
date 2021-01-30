package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.UsersDAO;
import be.atc.LocacarJSF.dao.UsersDAOImpl;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;

import java.util.List;

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
        return false;
    }

    @Override
    public boolean delete(UsersEntity usersEntity) {
        return false;
    }

    @Override
    public boolean connexion(UsersEntity usersEntit) {
        return false;
    }

    @Override
    public List<UsersEntity> findAll() {
        return usersDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public UsersEntity findById(int id) {
        if (id != 0) {
            return usersDAO.findById(id);
        }
        return null;
    }
}
