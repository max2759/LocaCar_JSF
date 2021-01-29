package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.OptionsEntity;
import be.atc.LocacarJSF.services.OptionsServices;
import be.atc.LocacarJSF.services.OptionsServicesImpl;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Named(value = "optionsBean")
@ViewScoped
public class OptionsBean implements Serializable {

    private static final long serialVersionUID = -5483371744376024574L;
    public static Logger log = Logger.getLogger(OptionsBean.class);

    private OptionsServices optionsServices = new OptionsServicesImpl();
    private OptionsEntity optionsEntity = new OptionsEntity();
    private List<OptionsEntity> optionsEntities;

    private boolean showPopup;
    private boolean addOptionEntity;

    @PostConstruct
    public void init() {
        optionsEntities = optionsServices.findAll();
    }

    /*public void addOption() {
        optionsEntity.setLabel(optionsEntity.getLabel());
        optionsServices.add(optionsEntity);
    }*/

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
        log.info("Hide PopupModal ");
        showPopup = false;
    }

    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {
        log.info("Save edit");
        if (addOptionEntity) {
            log.info("Add entity");
            optionsServices.add(optionsEntity);
        } else {
            log.info("update entity");
            optionsServices.update(optionsEntity);
        }
        init();
        hidePopupModal();
    }

    /**
     * Méthode pour retourner les paramètres récupéré
     *
     * @param name
     * @return
     */
    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }

//    public void updateOption() {
//
//        log.info("début de l'update");
//
//        OptionsEntity optionsEntityModify;
//        optionsEntityModify = optionsServices.findById(optionsEntity.getId());
//        optionsEntityModify.setLabel(optionsEntity.getLabel());
//
//        log.info("id Ancienne entité : " + optionsEntity.getId());
//        log.info("id nouvelle entité : " + optionsEntityModify.getId());
//        log.info("label Ancienne entité : " + optionsEntity.getLabel());
//        log.info("label nouvelle entité : " + optionsEntityModify.getLabel());
//
//        optionsServices.update(optionsEntityModify);
//    }


    ///// getters and setters /////////////

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
}
