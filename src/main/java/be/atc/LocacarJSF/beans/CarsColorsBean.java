package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;
import be.atc.LocacarJSF.services.CarsColorsServices;
import be.atc.LocacarJSF.services.CarsColorsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named(value = "carsColorsBean")
@ViewScoped
public class CarsColorsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 7509083962687295321L;
    public static Logger log = Logger.getLogger(CarsColorsBean.class);
    private CarsColorsServices carsColorsServices = new CarsColorsServicesImpl();
    private CarsColorsEntity carsColorsEntity = new CarsColorsEntity();
    private List<CarsColorsEntity> carsColorsEntities;

    private boolean showPopup;

    private boolean addCarsColorsEntity;
    private String success;
    private String fail;

    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageColors() {
        return "colors";
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carsColorsEntities
     */
    @PostConstruct
    public void init() {
        carsColorsEntities = carsColorsServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    /**
     * Ouvrir le popup d'édition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addCarsColorsEntity = false;
            int idCarsColors = parseInt(getParam("id"));
            carsColorsEntity = carsColorsServices.findById(idCarsColors);
        } else {
            addCarsColorsEntity = true;
            carsColorsEntity = new CarsColorsEntity();
        }
    }


    /**
     * Fermer le popup d'edition ou d'ajout
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal");
        initialisationFields();
        showPopup = false;
    }

    /**
     * Repetition code for add optionEntity
     */
    public void functionAddOption() {
        carsColorsServices.add(carsColorsEntity);
        success = JsfUtils.returnMessage(getLocale(), "fxs.carsColors.succesAdd");
    }

    /**
     * Repetition code for update optionEntity
     */
    public void functionUpdateOption() {
        carsColorsServices.update(carsColorsEntity);
        success = JsfUtils.returnMessage(getLocale(), "fxs.carsColors.successUpdate");
    }

    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {

        List<CarsColorsEntity> carsColorsEntityList = carsColorsServices.findByLabel(carsColorsEntity.getLabel());
        initialisationFields();

        log.info("Save edit");
        if ((addCarsColorsEntity) && (carsColorsEntityList.isEmpty())) {
            functionAddOption();
        } else if ((!addCarsColorsEntity) && (carsColorsEntityList.isEmpty())) {
            functionUpdateOption();
        } else if ((!addCarsColorsEntity) && (carsColorsEntityList.size() == 1)) {
            CarsColorsEntity ce = carsColorsEntityList.get(0);

            if (ce.getId() == carsColorsEntity.getId()) {
                functionUpdateOption();
            } else {
                fail = JsfUtils.returnMessage(getLocale(), "fxs.carsColors.errorAdd");
            }
        } else {
            fail = JsfUtils.returnMessage(getLocale(), "fxs.carsColors.errorAdd");
        }

        init();
    }


    ////// Getters and setters /////

    public List<CarsColorsEntity> getCarsColorsEntities() {
        return carsColorsEntities;
    }

    public void setCarsColorsEntities(List<CarsColorsEntity> carsColorsEntities) {
        this.carsColorsEntities = carsColorsEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public CarsColorsServices getCarsColorsServices() {
        return carsColorsServices;
    }

    public void setCarsColorsServices(CarsColorsServices carsColorsServices) {
        this.carsColorsServices = carsColorsServices;
    }

    public CarsColorsEntity getCarsColorsEntity() {
        return carsColorsEntity;
    }

    public void setCarsColorsEntity(CarsColorsEntity carsColorsEntity) {
        this.carsColorsEntity = carsColorsEntity;
    }

    public boolean isAddCarsColorsEntity() {
        return addCarsColorsEntity;
    }

    public void setAddCarsColorsEntity(boolean addCarsColorsEntity) {
        this.addCarsColorsEntity = addCarsColorsEntity;
    }
}
