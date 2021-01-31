package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.InsurancesServices;
import be.atc.LocacarJSF.services.InsurancesServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named(value = "insurancesBean")
@ViewScoped
public class InsurancesBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;

    private InsurancesEntity insurancesEntity;
    private InsurancesServices insurancesServices = new InsurancesServicesImpl();
    private List<InsurancesEntity> insurancesEntities;

    private boolean showPopup;
    private boolean addEntity;
    private String success;
    private String fail;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste insurancesEntities
     */
    @PostConstruct
    public void init() {
        log.info("Post Construct");
        insurancesEntities = insurancesServices.findAll();
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
        initialisationFields();
        showPopup = false;
    }

    /**
     * Repetition code for update entity
     */
    public void functionUpdateEntity() {
        log.info("Update entity");
        insurancesServices.update(insurancesEntity);
        success = JsfUtils.returnMessage(locale, "fxs.insurances.successUpdate");
    }

    /**
     * Repetition code for add Entity
     */
    public void functionAddEntity() {
        log.info("Add entity");
        insurancesServices.add(insurancesEntity);
        success = JsfUtils.returnMessage(locale, "fxs.insurances.successAdd");
    }

    /**
     * Vérifie et sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {
        List<InsurancesEntity> insurancesEntitiesTest = insurancesServices.findByLabel(insurancesEntity.getLabel());
        initialisationFields();
        log.info("Save edit");

        if ((addEntity) && (insurancesEntitiesTest.isEmpty())) {
            functionAddEntity();
        } else if ((!addEntity) && (insurancesEntitiesTest.isEmpty())) {
            functionUpdateEntity();
        } else if ((!addEntity) && (insurancesEntitiesTest.size() == 1)) {
            InsurancesEntity test = insurancesEntitiesTest.get(0);

            if (test.getId() == insurancesEntity.getId()) {
                functionUpdateEntity();
            } else {
                fail = JsfUtils.returnMessage(locale, "fxs.insurances.errorAdd");
            }
        } else {
            fail = JsfUtils.returnMessage(locale, "fxs.insurances.errorAdd");
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

        insurancesEntity.setActive(!insurancesEntity.isActive());
        insurancesServices.update(insurancesEntity);
        init();
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
