package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.InsurancesServices;
import be.atc.LocacarJSF.services.InsurancesServicesImpl;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named(value = "insurancesBean")
@RequestScoped
public class InsurancesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(InsurancesBean.class);

    private InsurancesEntity insurancesEntity;
    private InsurancesServices insurancesServices;
    private List<InsurancesEntity> insurancesEntities;


    @PostConstruct
    public void init() {
        log.info("Post Construct");
        insurancesServices = new InsurancesServicesImpl();
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
}
