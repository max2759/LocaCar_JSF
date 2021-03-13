package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;
import be.atc.LocacarJSF.services.RolesPermissionsServices;
import be.atc.LocacarJSF.services.RolesPermissionsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Named(value = "rolesPermissionsBean")
@SessionScoped

public class RolesPermissionsBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(be.atc.LocacarJSF.beans.RolesPermissionsBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private RolesPermissionsEntity rolesPermissionsEntity;
    private RolesPermissionsServices rolesPermissionsServices = new RolesPermissionsServicesImpl();
    private List<RolesPermissionsEntity> rolesPermissionsEntities;

    private boolean showPopupAdd;
    private boolean showPopupEdit;
    private String success;
    private String fail;
    private boolean editRolesPermissionsEntity = false;
    private boolean addRolesPermissionsEntity = true;
    @Inject
    private be.atc.LocacarJSF.beans.RolesBean rolesBean;
    @Inject
    private be.atc.LocacarJSF.beans.PermissionsBean permissionsBean;

    public void init() {
       // rolesPermissionsEntity = new RolesPermissionsEntity();
        rolesPermissionsEntities = rolesPermissionsServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }


    public void add() throws ParseException, NoSuchAlgorithmException {

        log.info("begin addRoleBean");

        log.info("role: "+rolesBean.getRolesEntity().getId());
        int idRole = rolesBean.getRolesEntity().getId();
        int idPerm =  permissionsBean.getPermissionsEntity().getId();

        rolesPermissionsEntity.setRolesByIdRoles(rolesBean.findById(idRole));
        rolesPermissionsEntity.setPermissionsByIdPermissions(permissionsBean.findById(idPerm));

        log.info("id recu du form perm : " + rolesPermissionsEntity.getPermissionsByIdPermissions());
        log.info("label recu du form role: " + rolesPermissionsEntity.getRolesByIdRoles());
        //log.info("num de reole: " + rolesPermissionsBean.findById(1));
        rolesPermissionsServices.add(rolesPermissionsEntity);
        log.info("role inscrit");
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModalAdd() {
        log.info("show popupModal add");
        showPopupAdd = true;
        addRolesPermissionsEntity = true;
        rolesPermissionsEntity = new RolesPermissionsEntity();
    }

    public void showPopupModalEdit() {
        log.info("show popupmodal edit");
        showPopupEdit = true;
        editRolesPermissionsEntity = true;
        int idRolesPerm = parseInt(getParam("idRolePerm"));
        log.info("idRolePerm in popup " + idRolesPerm);
        rolesPermissionsEntity = rolesPermissionsServices.findById(idRolesPerm);
    }

    /**
     * Fermer le popup d'edition ou d'ajout
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal");
        initialisationFields();
        showPopupAdd = false;
        showPopupEdit = false;
    }

    public void delete() {
        log.info("begin deleteroleperm");

        //faut appeler le service
        int idRolesPerm = parseInt(getParam("id"));
        log.info(idRolesPerm);
        rolesPermissionsEntity = rolesPermissionsServices.findById(idRolesPerm);
        rolesPermissionsServices.delete(idRolesPerm);

        log.info("end deletereoleperm ");

    }


    /**
     * Sauvegarde l'entité ajouté ou modifié !
     */
    public void saveEdit() {
        log.info("begin saveEdit");
        RolesPermissionsEntity rolesPermisionsEntitiesById = rolesPermissionsServices.findById(rolesPermissionsEntity.getId());
        initialisationFields();
        functionUpdateRolePermissions();
        init();
    }

     /*   public void functionAddRolePermissions() {
            log.info("appel a fonctionAdd das les perm");
            rolesPermissionsServices.add(rolesPermissionsEntity);
            success = JsfUtils.returnMessage(locale, "fxs.options.succesAdd");
        }  ==> similaire a add() */

    /**
     * Repetition code for update optionEntity
     */
    public void functionUpdateRolePermissions() {
        log.info("appel a fonctionUpdateRole dans les permissions");
        rolesPermissionsServices.update(rolesPermissionsEntity);
        success = JsfUtils.returnMessage(locale, "fxs.options.successUpdate");
    }


    public RolesPermissionsEntity findById(int id) {
        log.info("begin findByOd in bean");
        return rolesPermissionsServices.findById(id);
    }


    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    ///////////////////////////////////////
    ///// getters and setter //////////////
    //////////////////////////////////////


    public RolesBean getRolesBean() {
        return rolesBean;
    }

    public void setRolesBean(RolesBean rolesBean) {
        this.rolesBean = rolesBean;
    }

    public RolesPermissionsEntity getRolesPermissionsEntity() {
        return rolesPermissionsEntity;
    }

    public void setRolesPermissionsEntity(RolesPermissionsEntity rolesPermissionsEntity) {
        this.rolesPermissionsEntity = rolesPermissionsEntity;
    }

    public RolesPermissionsServices getRolesPermissionsServices() {
        return rolesPermissionsServices;
    }

    public void setRolesPermissionsServices(RolesPermissionsServices rolesPermissionsServices) {
        this.rolesPermissionsServices = rolesPermissionsServices;
    }

    public List<RolesPermissionsEntity> getRolesPermissionsEntities() {
        return rolesPermissionsEntities;
    }

    public void setRolesPermissionsEntities(List<RolesPermissionsEntity> rolesPermissionsEntities) {
        this.rolesPermissionsEntities = rolesPermissionsEntities;
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

    public boolean isShowPopupEdit() {
        return showPopupEdit;
    }

    public void setShowPopupEdit(boolean showPopupEdit) {
        this.showPopupEdit = showPopupEdit;
    }

    public boolean isEditRolesPermissionsEntity() {
        return editRolesPermissionsEntity;
    }

    public void setEditRolesPermissionsEntity(boolean editRolesPermissionsEntity) {
        this.editRolesPermissionsEntity = editRolesPermissionsEntity;
    }

    public boolean isAddRolesPermissionsEntity() {
        return addRolesPermissionsEntity;
    }

    public void setAddRolesPermissionsEntity(boolean addRolesPermissionsEntity) {
        this.addRolesPermissionsEntity = addRolesPermissionsEntity;
    }
}





