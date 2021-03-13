package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import be.atc.LocacarJSF.services.CarsOptionsServices;
import be.atc.LocacarJSF.services.CarsOptionsServicesImpl;
import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carsBean")
@SessionScoped
public class CarsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -293903106522268390L;

    private CarsEntity carsEntity;
    private CarsServices carsServices = new CarsServicesImpl();
    private CarsOptionsEntity carsOptionsEntity;
    private final CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    private List<CarsEntity> carsEntities;

    @Inject
    private PicturesBean picturesBean;

    @Inject
    private CarsOptionsBean carsOptionsBean;


    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     */
    @PostConstruct
    public void init() {
        log.info("Init cars");
        carsEntity = new CarsEntity();
        carsEntities = carsServices.findAll();
    }

    /**
     * add Car entity to DB
     *
     * @throws ServletException
     * @throws IOException
     */
    public void addCar() throws ServletException, IOException {
        log.info("Début ajout voiture");

        log.info(carsEntity);
        carsEntity.setActive(true);
        carsServices.add(carsEntity);
        carsOptionsBean.addOptionsToCarsOptions(carsEntity);
        picturesBean.save(carsEntity);
    }


    public void updateAddedCar(CarsEntity carsEntity) throws ServletException, IOException {
        log.info("Début de l'update de la voiture");

        log.info(carsEntity);
        carsServices.update(carsEntity);
        carsOptionsBean.addOptionsToCarsOptions(carsEntity);
        picturesBean.save(carsEntity);
    }


    /**
     * Return a car entity by passing an ID
     *
     * @param idCars
     * @return carsEntity
     */
    public CarsEntity findCarsById(int idCars) {
        return carsServices.findById(idCars);
    }

    /**
     * Set active => False or False => true
     *
     * @param c CarsEntity
     * @return CarsEntity
     */
    protected CarsEntity setActiveCarFalse(CarsEntity c) {
        c.setActive(!c.isActive());
        return c;
    }


    /**
     * Update CarEntity
     *
     * @param c CarsEntity
     * @return boolean
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
