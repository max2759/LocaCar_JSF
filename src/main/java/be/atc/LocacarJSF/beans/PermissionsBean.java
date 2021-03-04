package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.PermissionsEntity;
import be.atc.LocacarJSF.services.PermissionsServices;
import be.atc.LocacarJSF.services.PermissionsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Named(value = "permissionsBean")
@SessionScoped
public class PermissionsBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(PermissionsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private PermissionsEntity permissionsEntity;
    private PermissionsServices permissionsServices = new PermissionsServicesImpl();
    private List<PermissionsEntity> permissionsEntities;

    private boolean showPopup;
    private String success;
    private String fail;

    @Inject
    private PermissionsBean permissionsBean;

    @PostConstruct
    public void postConstruct() {
        log.info("begin postConstruct permissionsBean");
        init();
    }

    public void init() {
        permissionsEntity = new PermissionsEntity();
        //creer quelque chose avec permissions.findByIdUser afin de recuperer une liste de permission ?
        permissionsEntities = permissionsServices.findAll();
        log.info("permissionsEntities dans init permissionsBean "+permissionsEntities);
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }


    public PermissionsEntity findById(int id) {
        return permissionsServices.findById(id);
    }


    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    ///////////////////////////////////////
    ///// getters and setter //////////////
    //////////////////////////////////////



    public List<SelectItem> getPermissionsEntitiesSelectItems() {
        log.info("begin getPermissionsEntitiesSelectItems() + PermissionsEntities "+permissionsEntities);
        return permissionsEntities.stream().map(c -> new SelectItem(c.getId(), c.getLabel())).collect(Collectors.toList());
    }



    public PermissionsEntity getPermissionsEntity() {
        return permissionsEntity;
    }

    public void setPermissionsEntity(PermissionsEntity permissionsEntity) {
        this.permissionsEntity = permissionsEntity;
    }

    public PermissionsServices getPermissionsServices() {
        return permissionsServices;
    }

    public void setPermissionsServices(PermissionsServices permissionsServices) {
        this.permissionsServices = permissionsServices;
    }

    public List<PermissionsEntity> getPermissionsEntities() {
        return permissionsEntities;
    }

    public void setPermissionsEntities(List<PermissionsEntity> permissionsEntities) {
        this.permissionsEntities = permissionsEntities;
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
