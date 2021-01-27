package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.UsersServices;
import be.atc.LocacarJSF.services.UsersServicesImpl;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "usersBean")
@RequestScoped
public class UsersBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(UsersBean.class);

    private UsersEntity usersEntity = new UsersEntity();
    private UsersServices usersServices = new UsersServicesImpl();
    private List<UsersEntity> usersEntities;

    public void init() {
        usersEntities = usersServices.findAll();
    }

    public void connexion() {
        log.info("je suis dans le connexion");
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public UsersServices getUsersServices() {
        return usersServices;
    }

    public void setUsersServices(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    public List<UsersEntity> getUsersEntities() {
        return usersEntities;
    }

    public void setUsersEntities(List<UsersEntity> usersEntities) {
        this.usersEntities = usersEntities;
    }

    //verif mdp, ...

    //formulaire : toutes les données vont vers le bean qui vont partir dans le service qui va faire un add,
    // qui va appeler le DAO et enregsitrer en db l'inscription.
    // C'est au niveau des validator qu'on check que l'user est pas un con


}
