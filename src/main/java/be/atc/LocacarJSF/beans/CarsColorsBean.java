package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;
import be.atc.LocacarJSF.services.CarsColorsServices;
import be.atc.LocacarJSF.services.CarsColorsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Maximilien - Zabbara
 */
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

    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageColors() {
        return "colors?faces-redirect=true";
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste carsColorsEntities
     */
    @PostConstruct
    public void init() {
        carsColorsEntities = carsColorsServices.findAll();
    }


    /**
     * Open modal for update/add
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
        showPopup = false;
    }

    /**
     * Add cars colors entity
     */
    public void functionAddCarsColors() {
        log.info("CarsColorsBean : add CarsColors");

        FacesContext context = FacesContext.getCurrentInstance();
        carsColorsServices.add(carsColorsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.carsColors.succesAdd"), null));
    }

    /**
     * Update cars colors entity
     */
    public void functionUpdateCarsColors() {
        log.info("CarsColorsBean : update CarsColors");

        FacesContext context = FacesContext.getCurrentInstance();
        carsColorsServices.update(carsColorsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.carsColors.successUpdate"), null));
    }

    /**
     * Save updated or added CarsColors
     */
    public void saveEdit() {

        FacesContext context = FacesContext.getCurrentInstance();

        List<CarsColorsEntity> carsColorsEntityList = carsColorsServices.findByLabelList(carsColorsEntity.getLabel());

        log.info("Save edit");
        if ((addCarsColorsEntity) && (carsColorsEntityList.isEmpty())) {
            functionAddCarsColors();
        } else if ((!addCarsColorsEntity) && (carsColorsEntityList.isEmpty())) {
            functionUpdateCarsColors();
        } else if ((!addCarsColorsEntity) && (carsColorsEntityList.size() == 1)) {
            CarsColorsEntity ce = carsColorsEntityList.get(0);

            if (ce.getId() == carsColorsEntity.getId()) {
                functionUpdateCarsColors();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.carsColors.errorAdd"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.carsColors.errorAdd"), null));
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
