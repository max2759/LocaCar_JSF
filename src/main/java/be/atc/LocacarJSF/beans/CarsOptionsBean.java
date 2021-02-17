package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.CarsOptionsServices;
import be.atc.LocacarJSF.services.CarsOptionsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "carsOptionsBean")
@RequestScoped
public class CarsOptionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -8836402876857939542L;

    private CarsOptionsEntity carsOptionsEntity;
    private List<CarsOptionsEntity> carsOptionsEntityList;
    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    private List<OptionsEntity> optionsEntityList = new ArrayList<>();
    private int idCars = 13;

    @Inject
    private CarsBean carsBean;

    @Inject
    private OptionsBean optionsBean;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carEntities
     */
    @PostConstruct
    public void init() {
        carsOptionsEntity = new CarsOptionsEntity();
    }

    public void addOptionsToCarsOptions(CarsEntity carsEntity) {

        log.info("Début de la sauvegarde");

        log.info("entité liste :" + optionsEntityList);

        for (OptionsEntity optionsEntity : optionsEntityList) {
            init();
            carsOptionsEntity.setCarsByIdCars(carsEntity);
            carsOptionsEntity.setOptionsByIdOptions(optionsEntity);
            carsOptionsServices.add(carsOptionsEntity);
        }

        log.info("Bien sauvegardé");

    }

    /// getters setters ///


    public List<OptionsEntity> getOptionsEntityList() {
        return optionsEntityList;
    }

    public void setOptionsEntityList(List<OptionsEntity> optionsEntityList) {
        this.optionsEntityList = optionsEntityList;
    }
}
