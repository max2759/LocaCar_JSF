package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsGearboxEntity;
import be.atc.LocacarJSF.services.CarsGearboxServices;
import be.atc.LocacarJSF.services.CarsGearboxServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carsGearboxBean")
@ViewScoped
public class CarsGearboxBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 1858793462044747031L;
    private CarsGearboxServices carsGearboxServices = new CarsGearboxServicesImpl();
    private CarsGearboxEntity carsGearboxEntity = new CarsGearboxEntity();
    private List<CarsGearboxEntity> carsGearboxEntities;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste car carsGearboxEntities
     */
    @PostConstruct
    public void init() {
        log.info("Init carsGearbox");
        carsGearboxEntities = carsGearboxServices.findAll();
    }

    ///// Getters and setters /////


    public CarsGearboxServices getCarsGearboxServices() {
        return carsGearboxServices;
    }

    public void setCarsGearboxServices(CarsGearboxServices carsGearboxServices) {
        this.carsGearboxServices = carsGearboxServices;
    }

    public CarsGearboxEntity getCarsGearboxEntity() {
        return carsGearboxEntity;
    }

    public void setCarsGearboxEntity(CarsGearboxEntity carsGearboxEntity) {
        this.carsGearboxEntity = carsGearboxEntity;
    }

    public List<CarsGearboxEntity> getCarsGearboxEntities() {
        return carsGearboxEntities;
    }

    public void setCarsGearboxEntities(List<CarsGearboxEntity> carsGearboxEntities) {
        this.carsGearboxEntities = carsGearboxEntities;
    }
}
