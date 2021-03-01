package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.InsurancesServices;
import be.atc.LocacarJSF.services.InsurancesServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Younes - Arifi
 * Insurances Bean
 */
@Named(value = "insurancesBean")
@ApplicationScoped
public class InsurancesBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;

    private InsurancesEntity insurancesEntity;
    private final InsurancesServices insurancesServices = new InsurancesServicesImpl();
    private List<InsurancesEntity> insurancesEntities;
    private List<InsurancesEntity> insurancesEntitiesActive;

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
     * Find All insurances Active. For basket.
     */
    public void findAllInsurancesActive() {
        insurancesEntitiesActive = insurancesServices.findAllActiveInsurance();
    }

    /**
     * Open popup when edit or add
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addEntity = false;
            int idInsurance = parseInt(getParam("id"));
            insurancesEntity = findInsuranceById(idInsurance);
        } else {
            addEntity = true;
            insurancesEntity = new InsurancesEntity();
        }
    }

    /**
     * Close popup
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal ");
        showPopup = false;
    }

    /**
     * Repetition code for update entity
     */
    public void functionUpdateEntity() {
        log.info("Update entity");

        FacesContext context = FacesContext.getCurrentInstance();

        insurancesServices.update(insurancesEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.insurances.successUpdate"), null));
    }

    /**
     * Repetition code for add Entity
     */
    public void functionAddEntity() {
        log.info("Add entity");
        FacesContext context = FacesContext.getCurrentInstance();

        insurancesServices.add(insurancesEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.insurances.successAdd"), null));
    }

    /**
     * check and save entity
     */
    public void saveEdit() {
        log.info("Save edit");
        FacesContext context = FacesContext.getCurrentInstance();

        List<InsurancesEntity> insurancesEntitiesTest = insurancesServices.findByLabel(insurancesEntity.getLabel());

        if ((addEntity) && (insurancesEntitiesTest.isEmpty())) {
            functionAddEntity();
        } else if ((!addEntity) && (insurancesEntitiesTest.isEmpty())) {
            functionUpdateEntity();
        } else if ((!addEntity) && (insurancesEntitiesTest.size() == 1)) {
            InsurancesEntity test = insurancesEntitiesTest.get(0);

            if (test.getId() == insurancesEntity.getId()) {
                functionUpdateEntity();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.insurances.errorAdd"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.insurances.errorAdd"), null));
        }
        init();
    }

    /**
     * Delete or reactive Entity
     */
    public void deleteOrActivateInsurance() {
        log.info("Delete or reactivate insurance");

        FacesContext context = FacesContext.getCurrentInstance();

        int idInsurance = parseInt(getParam("id"));
        InsurancesEntity insurancesEntity = findInsuranceById(idInsurance);

        insurancesEntity.setActive(!insurancesEntity.isActive());
        if (insurancesServices.update(insurancesEntity)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.insurances.successDelete"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.insurances.errorDelete"), null));
        }
        init();
    }

    public InsurancesEntity findInsuranceById(int idInsurance) {
        return insurancesServices.findById(idInsurance);
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

    public List<InsurancesEntity> getInsurancesEntitiesActive() {
        return insurancesEntitiesActive;
    }

    public void setInsurancesEntitiesActive(List<InsurancesEntity> insurancesEntitiesActive) {
        this.insurancesEntitiesActive = insurancesEntitiesActive;
    }
}
