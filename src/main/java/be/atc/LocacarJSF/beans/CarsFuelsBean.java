package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsFuelsEntity;
import be.atc.LocacarJSF.services.CarsFuelsServices;
import be.atc.LocacarJSF.services.CarsFuelsServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carsFuelsBean")
@ViewScoped
public class CarsFuelsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -7221983297780954181L;
    private CarsFuelsServices carsFuelsServices = new CarsFuelsServicesImpl();
    private CarsFuelsEntity carsFuelsEntity = new CarsFuelsEntity();
    private List<CarsFuelsEntity> carsFuelsEntities;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste car carsGearboxEntities
     */
    @PostConstruct
    public void init() {
        log.info("Init carsFuels");
        carsFuelsEntities = carsFuelsServices.findAll();
    }

    ///// Getters and setters ////


    public CarsFuelsServices getCarsFuelsServices() {
        return carsFuelsServices;
    }

    public void setCarsFuelsServices(CarsFuelsServices carsFuelsServices) {
        this.carsFuelsServices = carsFuelsServices;
    }

    public CarsFuelsEntity getCarsFuelsEntity() {
        return carsFuelsEntity;
    }

    public void setCarsFuelsEntity(CarsFuelsEntity carsFuelsEntity) {
        this.carsFuelsEntity = carsFuelsEntity;
    }

    public List<CarsFuelsEntity> getCarsFuelsEntities() {
        return carsFuelsEntities;
    }

    public void setCarsFuelsEntities(List<CarsFuelsEntity> carsFuelsEntities) {
        this.carsFuelsEntities = carsFuelsEntities;
    }
}
