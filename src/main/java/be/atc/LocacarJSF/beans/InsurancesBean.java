package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.InsurancesServices;
import be.atc.LocacarJSF.services.InsurancesServicesImpl;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Named(value = "insurancesBean")
@ViewScoped
public class InsurancesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(InsurancesBean.class);

    private InsurancesEntity insurancesEntity;
    private InsurancesServices insurancesServices = new InsurancesServicesImpl();
    private List<InsurancesEntity> insurancesEntities;

    private boolean showPopup;
    private boolean addEntity;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste insurancesEntities
     */
    @PostConstruct
    public void init() {
        log.info("Post Construct");
        insurancesEntities = insurancesServices.findAll();
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addEntity = false;
            int idInsurance = parseInt(getParam("id"));
            insurancesEntity = insurancesServices.findById(idInsurance);
        } else {
            addEntity = true;
            insurancesEntity = new InsurancesEntity();
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
        if (addEntity) {
            log.info("Add entity");
            insurancesServices.add(insurancesEntity);
        } else {
            log.info("update entity");
            insurancesServices.update(insurancesEntity);
        }
        init();
    }

    /**
     * Supprime ou réactive l'entité !!
     */
    public void deleteOrActivateInsurance() {
        log.info("Delete or reactivate insurance");

        int idInsurance = parseInt(getParam("id"));
        InsurancesEntity insurancesEntity = insurancesServices.findById(idInsurance);

        if (insurancesEntity.isActive()) {
            insurancesEntity.setActive(false);
        } else {
            insurancesEntity.setActive(true);
        }
        insurancesServices.update(insurancesEntity);
        init();
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

    public List<InsurancesEntity> getInsurancesEntities() {
        return insurancesEntities;
    }

    public void setInsurancesEntities(List<InsurancesEntity> insurancesEntities) {
        this.insurancesEntities = insurancesEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public InsurancesEntity getInsurancesEntity() {
        return insurancesEntity;
    }

    public void setInsurancesEntity(InsurancesEntity insurancesEntity) {
        this.insurancesEntity = insurancesEntity;
    }

    public boolean isAddEntity() {
        return addEntity;
    }

    public void setAddEntity(boolean addEntity) {
        this.addEntity = addEntity;
    }
}
