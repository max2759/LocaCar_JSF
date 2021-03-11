package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsTypesEntity;
import be.atc.LocacarJSF.services.CarsTypesServices;
import be.atc.LocacarJSF.services.CarsTypesServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carTypesbean")
@ViewScoped
public class CarsTypesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 4026430227698755131L;
    private CarsTypesServices carsTypesServices = new CarsTypesServicesImpl();
    private CarsTypesEntity carsTypesEntity = new CarsTypesEntity();
    private List<CarsTypesEntity> carsTypesEntities;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carTypesEntities
     */
    @PostConstruct
    public void init() {
        log.info("Init car types services");
        carsTypesEntities = carsTypesServices.findAll();
    }

    /// Getters and setters ////


    public CarsTypesServices getCarsTypesServices() {
        return carsTypesServices;
    }

    public void setCarsTypesServices(CarsTypesServices carsTypesServices) {
        this.carsTypesServices = carsTypesServices;
    }

    public CarsTypesEntity getCarsTypesEntity() {
        return carsTypesEntity;
    }

    public void setCarsTypesEntity(CarsTypesEntity carsTypesEntity) {
        this.carsTypesEntity = carsTypesEntity;
    }

    public List<CarsTypesEntity> getCarsTypesEntities() {
        return carsTypesEntities;
    }

    public void setCarsTypesEntities(List<CarsTypesEntity> carsTypesEntities) {
        this.carsTypesEntities = carsTypesEntities;
    }
}
