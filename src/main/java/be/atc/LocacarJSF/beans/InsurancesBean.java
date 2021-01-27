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
    private InsurancesEntity insurancesEntityModify;
    private InsurancesServices insurancesServices = new InsurancesServicesImpl();
    private List<InsurancesEntity> insurancesEntities;

    private String label;

    private boolean showPopup;

    // post construc appelé en 1er mais aprés le constructeur si il y a
    @PostConstruct
    public void init() {
        log.info("Post Construct");
        insurancesEntities = insurancesServices.findAll();
    }


    public void showPopupModal() {
        log.info("Show PopupModal TRUE");
        showPopup = true;
        System.out.println("id : " + getParam("id"));
        int idInsurance = parseInt(getParam("id"));
        insurancesEntity = insurancesServices.findById(idInsurance);
    }

    public void hidePopupModal() {
        log.info("Show PopupModal False");
        showPopup = false;
    }

    public void saveEdit() {
        log.info("Save edit");
        InsurancesEntity insurancesEntityModify = null;
        insurancesEntityModify = insurancesServices.findById(insurancesEntity.getId());
        insurancesEntityModify.setLabel(insurancesEntity.getLabel());
        insurancesEntityModify.setPrice(insurancesEntity.getPrice());
        insurancesEntityModify.setDescription(insurancesEntity.getDescription());
        insurancesEntityModify.setActive(insurancesEntity.isActive());

        log.info("id Ancienne entité : " + insurancesEntity.getId());
        log.info("id nouvelle entité : " + insurancesEntityModify.getId());
        log.info("label Ancienne entité : " + insurancesEntity.getLabel());
        log.info("description Ancienne entité : " + insurancesEntity.getDescription());
        log.info("prix Ancienne entité : " + insurancesEntity.getPrice());
        log.info("active Ancienne entité : " + insurancesEntity.isActive());

        insurancesServices.update(insurancesEntityModify);

        insurancesEntities = insurancesServices.findAll();
    }


    // Méthode pour retourner les paramètres récupéré depuis l'url
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

    public InsurancesEntity getInsurancesEntityModify() {
        return insurancesEntityModify;
    }

    public void setInsurancesEntityModify(InsurancesEntity insurancesEntityModify) {
        this.insurancesEntityModify = insurancesEntityModify;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
