package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.services.CarsServices;
import be.atc.LocacarJSF.services.CarsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

@Named(value = "carsBean")
@RequestScoped
public class CarsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -293903106522268390L;

    private CarsEntity carsEntity;
    private CarsServices carsServices = new CarsServicesImpl();
    private List<CarsEntity> carsEntities;

    private Part file;

    @Inject
    private PicturesBean picturesBean;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     */
    @PostConstruct
    public void init() {
        carsEntity = new CarsEntity();
        carsEntities = carsServices.findAll();

    }

    public void upload() throws IOException {
        Scanner scanner = new Scanner(file.getInputStream());
    }


    public void addCar() {
        log.info("Début ajout voiture");

        carsEntity.setCarsPicturesByIdCarsPictures(picturesBean.getCarsPicturesEntity());
        carsEntity.setActive(true);

        carsServices.add(carsEntity);


    }

    /// getter and setters

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

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
