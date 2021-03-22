package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.CarsOptionsServices;
import be.atc.LocacarJSF.services.CarsOptionsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "carsOptionsBean")
@SessionScoped
public class CarsOptionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -8836402876857939542L;

    private CarsOptionsEntity carsOptionsEntity;
    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    private List<OptionsEntity> optionsEntityList = new ArrayList<>();

    @Inject
    private OptionsBean optionsBean;

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

    /**
     * Find all car options by cars ID
     *
     * @param idCars
     */
    protected void findAllCarOptionByIdCar(int idCars) {

        optionsEntityList = new ArrayList<>();
        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(idCars);

        for (CarsOptionsEntity c : carsOptionsEntityList) {
            optionsEntityList.add(c.getOptionsByIdOptions());
        }

    }

    /**
     * Delete Car option by looping through carsOptionsEntity
     *
     * @param carsEntity
     */
    public void deleteCarOption(CarsEntity carsEntity) {
        log.info("CarsOptionBean : DeleteCarOption");

        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(carsEntity.getId());

        for (CarsOptionsEntity c : carsOptionsEntityList) {
            log.info("Début foreach" + c.getId());
            carsOptionsServices.deleteCarOptionByID(c.getId());
        }
    }

    /**
     * Display all car option by Cars ID
     *
     * @param idCars
     * @return
     */
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
