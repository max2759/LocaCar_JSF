package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.OptionsServices;
import be.atc.LocacarJSF.services.OptionsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Named(value = "optionsBean")
@ViewScoped
public class OptionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -5483371744376024574L;
    public static Logger log = Logger.getLogger(OptionsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    private final OptionsServices optionsServices = new OptionsServicesImpl();
    private OptionsEntity optionsEntity = new OptionsEntity();
    private List<OptionsEntity> optionsEntities;
    private List<OptionsEntity> optionsEntityList = new ArrayList<>();

    private boolean showPopup;
    private boolean addOptionEntity;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageOption() {
        return "options??faces-redirect=true";
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        optionsEntities = optionsServices.findAll();
    }



    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addOptionEntity = false;
            int idOptions = parseInt(getParam("id"));
            optionsEntity = optionsServices.findById(idOptions);
        } else {
            addOptionEntity = true;
            optionsEntity = new OptionsEntity();
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
     * Add cars options entity
     */
    public void functionAddOption() {
        log.info("OptionsBean : add Options");

        FacesContext context = FacesContext.getCurrentInstance();
        optionsServices.add(optionsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.options.succesAdd"), null));
    }

    /**
     * Update cars options entity
     */
    public void functionUpdateOption() {
        log.info("OptionsBean : update Options");

        FacesContext context = FacesContext.getCurrentInstance();
        optionsServices.update(optionsEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.options.successUpdate"), null));

    }

    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {

        FacesContext context = FacesContext.getCurrentInstance();
        List<OptionsEntity> optionsEntitiesByLabel = optionsServices.findByLabel(optionsEntity.getLabel());

        log.info("Save edit");
        if ((addOptionEntity) && (optionsEntitiesByLabel.isEmpty())) {
            functionAddOption();
        } else if ((!addOptionEntity) && (optionsEntitiesByLabel.isEmpty())) {
            functionUpdateOption();
        } else if ((!addOptionEntity) && (optionsEntitiesByLabel.size() == 1)) {
            OptionsEntity oe = optionsEntitiesByLabel.get(0);

            if (oe.getId() == optionsEntity.getId()) {
                functionUpdateOption();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.options.errorAdd"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.options.errorAdd"), null));
        }

        init();
    }

    /*
     * Getters and Setters
     *
     */

    public OptionsEntity getOptionsEntity() {
        return optionsEntity;
    }

    public void setOptionsEntity(OptionsEntity optionsEntity) {
        this.optionsEntity = optionsEntity;
    }

    public List<OptionsEntity> getOptionsEntities() {
        return optionsEntities;
    }

    public void setOptionsEntities(List<OptionsEntity> optionsEntities) {
        this.optionsEntities = optionsEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public boolean isAddOptionEntity() {
        return addOptionEntity;
    }

    public void setAddOptionEntity(boolean addOptionEntity) {
        this.addOptionEntity = addOptionEntity;
    }

    public List<OptionsEntity> getOptionsEntityList() {
        return optionsEntityList;
    }

    public void setOptionsEntityList(List<OptionsEntity> optionsEntityList) {
        this.optionsEntityList = optionsEntityList;
    }
}
