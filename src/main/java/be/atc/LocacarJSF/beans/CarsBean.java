package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import be.atc.LocacarJSF.services.CarsOptionsServices;
import be.atc.LocacarJSF.services.CarsOptionsServicesImpl;
import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "carsBean")
@RequestScoped
public class CarsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -293903106522268390L;

    private CarsEntity carsEntity;
    private CarsServices carsServices = new CarsServicesImpl();
    private CarsOptionsEntity carsOptionsEntity;
    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    private List<CarsEntity> carsEntities;

    @Inject
    private PicturesBean picturesBean;

    @Inject
    private OptionsBean optionsBean;

    @Inject
    private CarsOptionsBean carsOptionsBean;


    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     */
    @PostConstruct
    public void init() {
        carsEntity = new CarsEntity();
        carsEntities = carsServices.findAll();
    }

    public void addCar() {
        log.info("Début ajout voiture");

        carsEntity.setActive(true);
        carsServices.add(carsEntity);
        carsOptionsBean.addOptionsToCarsOptions(carsEntity);
    }


    public CarsEntity findCarsById(int idCars) {
        return carsServices.findById(idCars);
    }

    /**
     * Set active => False
     *
     * @param c
     * @return
     */
    protected CarsEntity setActiveCarFalse(CarsEntity c) {
        c.setActive(!c.isActive());
        return c;
    }

    /**
     * Update CarEntity
     *
     * @param c
     * @return
     */
    protected boolean updateCar(CarsEntity c) {
        return carsServices.update(c);
    }

    /// getter and setters

    public CarsEntity getCarsEntity() {
        return carsEntity;
    }

    public void setCarsEntity(CarsEntity carsEntity) {
        this.carsEntity = carsEntity;
    }

    public CarsServices getCarsServices() {
        return carsServices;
    }

    public void setCarsServices(CarsServices carsServices) {
        this.carsServices = carsServices;
    }
}
