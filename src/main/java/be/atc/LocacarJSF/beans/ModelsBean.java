package be.atc.LocacarJSF.beans;


import be.atc.LocacarJSF.dao.entities.ModelsEntity;
import be.atc.LocacarJSF.services.BrandsServices;
import be.atc.LocacarJSF.services.BrandsServicesImpl;
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
import java.util.Locale;

import static java.lang.Integer.parseInt;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "modelsBean")
@ViewScoped
public class ModelsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 4362706276284973700L;
    public static Logger log = Logger.getLogger(ModelsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private ModelsServices modelsServices = new ModelsServicesImpl();
    private ModelsEntity modelsEntity = new ModelsEntity();
    private BrandsServices brandsServices = new BrandsServicesImpl();

    private List<ModelsEntity> modelsEntities;

    @Inject
    private BrandsBean brandsBean;

    private boolean showPopup;
    private boolean addModelsEntity;
    private boolean showModel;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageModels() {
        return "models";
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        showModel = false;
        modelsEntities = modelsServices.findAll();

    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
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

    /**
     * Fermer le popup d'edition ou d'ajout
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
     * Sauvegarde l'entité ajouté ou modifié !
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

        init();
    }

    public void findModelsByBrands() {
        modelsEntities = modelsServices.findModelsByBrandsId(modelsEntity.getBrandsByIdBrands().getId());
        showModel = true;
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
}
