package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.dao.entities.ModelsEntity;
import be.atc.LocacarJSF.services.ModelsServices;
import be.atc.LocacarJSF.services.ModelsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "modelsBean")
@ViewScoped
public class ModelsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 4362706276284973700L;
    public static Logger log = Logger.getLogger(ModelsBean.class);

    private ModelsServices modelsServices = new ModelsServicesImpl();
    private ModelsEntity modelsEntity = new ModelsEntity();

    private List<ModelsEntity> modelsEntities;

    private boolean showPopup;
    private boolean addModelsEntity;
    private boolean showModel;
    private boolean showInput;

    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageModels() {
        return "models?faces-redirect=true";
    }

    @Inject
    private AdsBean adsBean;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        showModel = false;
        showInput = true;
    }

    /**
     * Open add or update modal
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addModelsEntity = false;
            int idModels = parseInt(getParam("id"));
            modelsEntity = modelsServices.findById(idModels);
        } else {
            addModelsEntity = true;
            modelsEntity = new ModelsEntity();
        }
    }

    public void findAllModel() {
        modelsEntities = modelsServices.findAll();
    }

    /**
     * Close add or update modal
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal");
        showPopup = false;
    }


    /**
     * Add models entity
     */
    public void functionAddModels() {
        log.info("ModelsBean : add models");

        FacesContext context = FacesContext.getCurrentInstance();
        modelsServices.add(modelsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.models.succesAdd"), null));
    }

    /**
     * Update models entity
     */
    public void functionUpdateModels() {
        log.info("ModelsBean : update models");

        FacesContext context = FacesContext.getCurrentInstance();
        modelsServices.update(modelsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.models.successUpdate"), null));
    }

    /**
     * Save updated/added entity
     */
    public void saveEdit() {

        log.info("début de sauvegarde");
        FacesContext context = FacesContext.getCurrentInstance();
        List<ModelsEntity> modelsEntityList = modelsServices.findByLabel(modelsEntity.getLabel());


        log.info("Save edit");
        if ((addModelsEntity) && (modelsEntityList.isEmpty())) {
            functionAddModels();
        } else if ((!addModelsEntity) && (modelsEntityList.isEmpty())) {
            functionUpdateModels();
        } else if ((!addModelsEntity) && (modelsEntityList.size() == 1)) {
            ModelsEntity me = modelsEntityList.get(0);

            if (me.getId() == modelsEntity.getId()) {
                functionUpdateModels();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.models.errorAdd"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.models.errorAdd"), null));
        }

        findAllModel();
    }

    /**
     * Find models by brands ID
     */
    public void findModelsByBrands() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (modelsEntity.getBrandsByIdBrands() == null) {
            showModel = false;
            return;
        }

        if (modelsEntity.getBrandsByIdBrands().getId() == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.models.errorAdd"), null));
        } else {
            modelsEntities = modelsServices.findModelsByBrandsId(modelsEntity.getBrandsByIdBrands().getId());
        }

        showModel = !modelsEntities.isEmpty();
    }

    public void findModelsByBrandsID() {
        modelsEntities = modelsServices.findModelsByBrandsId(adsBean.getAdsEntity().getCarsByIdCars().getModelsByIdModels().getBrandsByIdBrands().getId());
    }

    /**
     * find Brands by ads ID
     */
    public void findBrandsByAds() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (adsBean.getAdsEntity().getCarsByIdCars().getModelsByIdModels().getBrandsByIdBrands().getId() == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.models.errorAdd"), null));
        } else {
            findModelsByBrandsID();
        }

        showInput = !modelsEntities.isEmpty();
    }

    //////Getters and setters

    public ModelsServices getModelsServices() {
        return modelsServices;
    }

    public void setModelsServices(ModelsServices modelsServices) {
        this.modelsServices = modelsServices;
    }

    public ModelsEntity getModelsEntity() {
        return modelsEntity;
    }

    public void setModelsEntity(ModelsEntity modelsEntity) {
        this.modelsEntity = modelsEntity;
    }

    public List<ModelsEntity> getModelsEntities() {
        return modelsEntities;
    }

    public void setModelsEntities(List<ModelsEntity> modelsEntities) {
        this.modelsEntities = modelsEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public boolean isAddModelsEntity() {
        return addModelsEntity;
    }

    public void setAddModelsEntity(boolean addModelsEntity) {
        this.addModelsEntity = addModelsEntity;
    }

    public boolean isShowModel() {
        return showModel;
    }

    public void setShowModel(boolean showModel) {
        this.showModel = showModel;
    }

    public boolean isShowInput() {
        return showInput;
    }

    public void setShowInput(boolean showInput) {
        this.showInput = showInput;
    }
}
