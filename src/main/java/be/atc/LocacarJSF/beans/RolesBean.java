package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.services.RolesServices;
import be.atc.LocacarJSF.services.RolesServicesImpl;
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

@Named(value = "rolesBean")
@SessionScoped
public class RolesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(RolesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private RolesEntity rolesEntity;
    private RolesServices rolesServices = new RolesServicesImpl();
    private List<RolesEntity> rolesEntities;

    private boolean showPopup;
    private boolean showPopupAdd;
    private String success;
    private String fail;
    private boolean editRolesEntity;
    private boolean addRolesEntity;
    @Inject
    private RolesBean rolesBean;

    @PostConstruct
    public void postConstruct() {
        log.info("begin postConstruct RolesBean");
        init();
    }

    public void init() {
        //creer quelque chose avec Roles.findByIdUser afin de recuperer une liste de role ?
        rolesEntity = new RolesEntity();
        rolesEntities = rolesServices.findAll();
        log.info("rolesEntities dans init rolesBean "+rolesEntities);

    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }


    public void addRole() throws ParseException, NoSuchAlgorithmException {

        log.info("begin addRoleBean");

        rolesEntity.setLabel(rolesEntity.getLabel());
        rolesEntity.setActive(true);

        log.info("label recu du form: " + rolesEntity.getLabel());
        log.info("num de reole: " + rolesBean.findById(1));
        rolesServices.add(rolesEntity);
        log.info("role inscrit");
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            log.info("param id est pas null dans showpopup");
            editRolesEntity = false;
            int idRoles = parseInt(getParam("id"));
            rolesEntity = rolesServices.findById(idRoles);
        } else {
            log.info("dans le else showpopup");
            editRolesEntity = true;
            rolesEntity = new RolesEntity();
        }
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModalAdd() {
        log.info("Show PopupModalAdd");
        showPopupAdd = true;
        addRolesEntity = true;
        rolesEntity = new RolesEntity();
    }

    /**
     * Fermer le popup d'edition ou d'ajout
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal");
        initialisationFields();
        showPopup = false;
        showPopupAdd = false;
    }


    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {


        List<RolesEntity> rolesEntitiesByLabel = rolesServices.findByLabel(rolesEntity.getLabel());
        initialisationFields();

        log.info("Save edit");
        if ((rolesEntitiesByLabel.isEmpty())) {
            functionUpdateRole();
        } else if ((rolesEntitiesByLabel.size() == 1)) {
            RolesEntity ue = rolesEntitiesByLabel.get(0);

            if (ue.getId() == rolesEntity.getId()) {
                functionUpdateRole();
            } else {
                fail = JsfUtils.returnMessage(locale, "fxs.roles.errorAdd");
            }
        } else {
            fail = JsfUtils.returnMessage(locale, "fxs.users.errorAdd");
        }

        init();
    }

    public void functionAddRole() {
        log.info("begin addrole");
        rolesServices.add(rolesEntity);
        success = JsfUtils.returnMessage(locale, "fxs.options.succesAdd");
    }

    /**
     * Repetition code for update optionEntity
     */
    public void functionUpdateRole() {
        rolesServices.update(rolesEntity);
        success = JsfUtils.returnMessage(locale, "fxs.options.successUpdate");
    }


    public RolesEntity findById(int id) {
        return rolesServices.findById(id);
    }


    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    ///////////////////////////////////////
    ///// getters and setter //////////////
    //////////////////////////////////////



    public List<SelectItem> getRolesEntitiesSelectItems() {
        log.info("begin getRolesEntitiesSelectItems() + rolesEntities "+rolesEntities);
        return rolesEntities.stream().map(c -> new SelectItem(c.getId(), c.getLabel())).collect(Collectors.toList());
    }



    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(be.atc.LocacarJSF.dao.entities.RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public RolesServices getRolesServices() {
        return rolesServices;
    }

    public void setRolesServices(RolesServices rolesServices) {
        this.rolesServices = rolesServices;
    }

    public List<RolesEntity> getRolesEntities() {
        return rolesEntities;
    }

    public void setRolesEntities(List<RolesEntity> rolesEntities) {
        this.rolesEntities = rolesEntities;
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

    public boolean isShowPopupAdd() {
        return showPopupAdd;
    }

    public void setShowPopupAdd(boolean showPopupAdd) {
        this.showPopupAdd = showPopupAdd;
    }
}
