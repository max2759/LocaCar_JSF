package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.OptionsServices;
import be.atc.LocacarJSF.services.OptionsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Named(value = "optionsBean")
@ViewScoped
public class OptionsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -5483371744376024574L;
    public static Logger log = Logger.getLogger(OptionsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();


    private OptionsServices optionsServices = new OptionsServicesImpl();
    private OptionsEntity optionsEntity = new OptionsEntity();
    private List<OptionsEntity> optionsEntities;

    private boolean showPopup;
    private boolean addOptionEntity;
    private String success;
    private String fail;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String toPageOption() {
        return "options";
    }

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        optionsEntities = optionsServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
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
        initialisationFields();
        showPopup = false;
    }

    /**
     * Repetition code for add optionEntity
     */
    public void functionAddOption() {
        optionsServices.add(optionsEntity);
        success = JsfUtils.returnMessage(locale, "fxs.options.succesAdd");
    }

    /**
     * Repetition code for update optionEntity
     */
    public void functionUpdateOption() {
        optionsServices.update(optionsEntity);
        success = JsfUtils.returnMessage(locale, "fxs.options.successUpdate");
    }

    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {

        List<OptionsEntity> optionsEntitiesByLabel = optionsServices.findByLabel(optionsEntity.getLabel());
        initialisationFields();

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
                fail = JsfUtils.returnMessage(locale, "fxs.options.errorAdd");
            }
        } else {
            fail = JsfUtils.returnMessage(locale, "fxs.options.errorAdd");
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
}
