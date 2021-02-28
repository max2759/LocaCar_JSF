package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import be.atc.LocacarJSF.services.CitiesServices;
import be.atc.LocacarJSF.services.CitiesServicesImpl;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Named(value = "citiesBean")
@SessionScoped
public class CitiesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(CitiesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private CitiesEntity citiesEntity;
    private CitiesServices citiesServices = new CitiesServicesImpl();
    private List<CitiesEntity> citiesEntities;

    private boolean showPopup;
    private String success;
    private String fail;

    @Inject
    private UsersBean usersBean;

    @PostConstruct
    public void postContruc() {
        findByUserId();
    }

    public void init() {
        citiesEntities = citiesServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    public CitiesEntity findById(int id) {
        return citiesServices.findById(id);
    }

    public void findByUserId() {
        citiesEntities = citiesServices.findByIdUser(usersBean.getUsersEntity().getId());
    }


    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    ///////////////////////////////////////
    ///// getters and setter //////////////
    //////////////////////////////////////


    public CitiesEntity getCitiesEntity() {
        return citiesEntity;
    }

    public void setCitiesEntity(CitiesEntity citiesEntity) {
        this.citiesEntity = citiesEntity;
    }

    public CitiesServices getCitiesServices() {
        return citiesServices;
    }

    public void setCitiesServices(CitiesServices citiesServices) {
        this.citiesServices = citiesServices;
    }

    public List<CitiesEntity> getCitiesEntities() {
        return citiesEntities;
    }

    public void setCitiesEntities(List<CitiesEntity> citiesEntities) {
        this.citiesEntities = citiesEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
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
