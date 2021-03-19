package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.CarsOptionsServices;
import be.atc.LocacarJSF.services.CarsOptionsServicesImpl;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carsOptionsBean")
@ViewScoped
public class CarsOptionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -8836402876857939542L;

    private CarsOptionsEntity carsOptionsEntity;
    private List<CarsOptionsEntity> carsOptionsEntityList;
    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    private List<OptionsEntity> optionsEntityList = new ArrayList<>();

    /**
     * PostConstruct : appelé après le constructeur.
     * get last updated CarsOptions Entity
     */
    @PostConstruct
    public void init() {
        carsOptionsEntity = new CarsOptionsEntity();
    }

    /**
     * take CarsEntity as option and save options id and cars id into carsOptions
     *
     * @param carsEntity
     */
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

    public void deleteCarOption(int id) {
        log.info("CarsOptionBean : DeleteCarOption");

        carsOptionsServices.delete(id);
    }

    public void updateCarOptions(CarsEntity carsEntity) {
        for (OptionsEntity optionsEntity : optionsEntityList) {
            carsOptionsEntity.setCarsByIdCars(carsEntity);
            carsOptionsEntity.setOptionsByIdOptions(optionsEntity);
            carsOptionsServices.update(carsOptionsEntity);
        }

    }

    public List<CarsOptionsEntity> findCarsOptionsByCarsId(int idCars) {
        return carsOptionsServices.findCarsOptionsByCarsId(idCars);
    }

    /// getters setters ///


    public List<OptionsEntity> getOptionsEntityList() {
        return optionsEntityList;
    }

    public void setOptionsEntityList(List<OptionsEntity> optionsEntityList) {
        this.optionsEntityList = optionsEntityList;
    }
}
