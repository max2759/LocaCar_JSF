package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.services.RolesServices;
import be.atc.LocacarJSF.services.RolesServicesImpl;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

public class RolesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(RolesBean.class);

    private RolesEntity rolesEntity = new RolesEntity();
    private RolesServices rolesServices = new RolesServicesImpl();
    private List<RolesEntity> rolesEntities;

    public void init() {
        rolesEntities = rolesServices.findAll();
    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(be.atc.LocacarJSF.dao.entities.RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public RolesServices getRolesServices() {
        return rolesServices;
    }

    public void setRolesServices(RolesServices rolesServices) {
        this.rolesServices = rolesServices;
    }

    public List<RolesEntity> getRolesEntities() {
        return rolesEntities;
    }

    public void setRolesEntities(List<RolesEntity> rolesEntities) {
        this.rolesEntities = rolesEntities;
    }

}
