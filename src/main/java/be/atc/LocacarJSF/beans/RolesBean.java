package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.services.RolesServices;
import be.atc.LocacarJSF.services.RolesServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
public class RolesBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(RolesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private RolesEntity rolesEntity;
    private RolesServices rolesServices = new RolesServicesImpl();
    private List<RolesEntity> rolesEntities;

    private boolean inList = false;
    private boolean showPopupEdit;
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
        //creer quelque chose avec Roles.findByIdUser afin de recuperer une liste de role ?   rolesEntity = new RolesEntity();
        if (inList == true) {
            rolesEntity = new RolesEntity();
        } else {
            rolesEntities = rolesServices.findAll();
        }

        log.info("rolesEntities dans init rolesBean " + rolesEntities);

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
    public void showPopupModalEdit() {
        log.info("Show PopupModal");
        showPopupEdit = true;
        int roleId = parseInt(getParam("id"));
        log.info("idRolePerm in popup " + roleId);
        rolesEntity = rolesServices.findById(roleId);
        log.info(rolesEntity.getId());
        log.info(rolesEntity.getLabel());
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

        showPopupEdit = false;
        showPopupAdd = false;
    }


    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {
        FacesContext context = FacesContext.getCurrentInstance();

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
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.roles.errorAdd"), null));
                //fail = JsfUtils.returnMessage(locale, "fxs.roles.errorAdd");
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.roles.errorAdd"), null));
            //  fail = JsfUtils.returnMessage(locale, "fxs.users.errorAdd");
        }

        init();
    }

    public void functionAddRole() {
        log.info("begin addrole");
        rolesServices.add(rolesEntity);
        success = JsfUtils.returnMessage(locale, "fxs.role.successAdd");
    }

    /**
     * Repetition code for update optionEntity
     */
    public void functionUpdateRole() {
        FacesContext context = FacesContext.getCurrentInstance();
        rolesServices.update(rolesEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.role.successUpdate"), null));

        // success = JsfUtils.returnMessage(locale, "fxs.role.successUpdate");
    }

    public void delete() {
        log.info("begin deleteUser logic");

        int idRole = parseInt(getParam("idDel"));
        log.info(idRole);
        rolesEntity = rolesServices.findById(idRole);
        //faut appeler le service aprés
        rolesEntity.setActive(false);
        rolesServices.update(rolesEntity);

        log.info("end deleteUser logic");


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
        log.info("begin getRolesEntitiesSelectItems() + rolesEntities " + rolesEntities);
        inList = true;
        init();
        inList = false;
        return rolesEntities.stream().map(c -> new SelectItem(c.getId(), c.getLabel())).collect(Collectors.toList());

    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
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

    public boolean isShowPopupEdit() {
        return showPopupEdit;
    }

    public void setShowPopupEdit(boolean showPopupEdit) {
        this.showPopupEdit = showPopupEdit;
    }

    public boolean isShowPopupAdd() {
        return showPopupAdd;
    }

    public void setShowPopupAdd(boolean showPopupAdd) {
        this.showPopupAdd = showPopupAdd;
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

    public boolean isEditRolesEntity() {
        return editRolesEntity;
    }

    public void setEditRolesEntity(boolean editRolesEntity) {
        this.editRolesEntity = editRolesEntity;
    }

    public boolean isAddRolesEntity() {
        return addRolesEntity;
    }

    public void setAddRolesEntity(boolean addRolesEntity) {
        this.addRolesEntity = addRolesEntity;
    }

    public RolesBean getRolesBean() {
        return rolesBean;
    }

    public void setRolesBean(RolesBean rolesBean) {
        this.rolesBean = rolesBean;
    }
}
