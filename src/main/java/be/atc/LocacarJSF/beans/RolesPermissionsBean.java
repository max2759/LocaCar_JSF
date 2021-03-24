package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.RolesPermissionsEntity;
import be.atc.LocacarJSF.services.RolesPermissionsServices;
import be.atc.LocacarJSF.services.RolesPermissionsServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
/**
 * @author Larché Marie-Elise
 */
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

    private boolean updateUsers;
    private boolean createUsers;
    private boolean deleteUsers;
    private boolean readUsers;

    private boolean createAds;
    private boolean updateAds;
    private boolean deleteAds;
    private boolean readAds;

    private boolean updateRoles;
    private boolean readRoles;
    private boolean deleteRoles;
    private boolean createRoles;

    private boolean updateColors;
    private boolean readColors;
    private boolean deleteColors;
    private boolean createColors;

    private boolean updateInsurances;
    private boolean readInsurances;
    private boolean deleteInsurances;
    private boolean createInsurances;

    private boolean updateOrders;
    private boolean readOrders;
    private boolean deleteOrders;
    private boolean createOrders;

    private boolean updateOptions;
    private boolean readOptions;
    private boolean deleteOptions;
    private boolean createOptions;

    private boolean updateModels;
    private boolean readModels;
    private boolean deleteModels;
    private boolean createModels;


    private boolean editRolesPermissionsEntity = false;
    private boolean addRolesPermissionsEntity = true;
    @Inject
    private be.atc.LocacarJSF.beans.RolesBean rolesBean;
    @Inject
    private be.atc.LocacarJSF.beans.PermissionsBean permissionsBean;
    @Inject
    private be.atc.LocacarJSF.beans.UsersBean usersBean;

    private List<RolesPermissionsEntity> listOfUserPermissions;


    public void init() {
        // rolesPermissionsEntity = new RolesPermissionsEntity();
        rolesPermissionsEntities = rolesPermissionsServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        log.info(parseInt(getParam("id")));
        int idRolesPerm = parseInt(getParam("id"));
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

    public boolean controlAdd(int idRole, int idPerm) {
        boolean control = false;

        RolesPermissionsEntity findByIdRoleAnIdPerm = rolesPermissionsServices.findByRoleAndPerm(idRole, idPerm);
        log.info(findByIdRoleAnIdPerm);

        if (findByIdRoleAnIdPerm == null) {
            control = true;
        }

        return control;
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
        FacesContext context = FacesContext.getCurrentInstance();
        log.info("appel a fonctionUpdateRole dans les permissions");
        int idRole = rolesBean.getRolesEntity().getId();
        int idPerm = permissionsBean.getPermissionsEntity().getId();

        boolean testAdd = controlAdd(idRole, idPerm);
        log.info(testAdd);

        if (testAdd) {
            rolesPermissionsEntity.setRolesByIdRoles(rolesBean.findById(idRole));
            rolesPermissionsEntity.setPermissionsByIdPermissions(permissionsBean.findById(idPerm));
            log.info("id recu du form perm : " + rolesPermissionsEntity.getPermissionsByIdPermissions());
            log.info("label recu du form role: " + rolesPermissionsEntity.getRolesByIdRoles());
            //log.info("num de reole: " + rolesPermissionsBean.findById(1));
            rolesPermissionsServices.update(rolesPermissionsEntity);
            //success = JsfUtils.returnMessage(locale, "fxs.rolePerm.successUpdate");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.rolePerm.successUpdate"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.rolesPerm.doble"), null));
            // fail = JsfUtils.returnMessage(getLocale(), "fxs.rolesPerm.doble");
        }

    }


    public RolesPermissionsEntity findById(int id) {
        log.info("begin findByOd in bean");
        return rolesPermissionsServices.findById(id);
    }

    public void add() throws ParseException, NoSuchAlgorithmException {

        log.info("begin addRoleBean");

        log.info("role: " + rolesBean.getRolesEntity().getId());
        int idRole = rolesBean.getRolesEntity().getId();
        int idPerm = permissionsBean.getPermissionsEntity().getId();

        rolesPermissionsEntity.setRolesByIdRoles(rolesBean.findById(idRole));
        rolesPermissionsEntity.setPermissionsByIdPermissions(permissionsBean.findById(idPerm));

        boolean testAdd = controlAdd(idRole, idPerm);
        log.info(testAdd);

        if (testAdd) {
            log.info("id recu du form perm : " + rolesPermissionsEntity.getPermissionsByIdPermissions());
            log.info("label recu du form role: " + rolesPermissionsEntity.getRolesByIdRoles());
            //log.info("num de reole: " + rolesPermissionsBean.findById(1));
            rolesPermissionsServices.add(rolesPermissionsEntity);
            success = JsfUtils.returnMessage(getLocale(), "fxs.rolePerm.successUpdate");
        } else {
            fail = JsfUtils.returnMessage(getLocale(), "fxs.rolesPerm.doble");
        }

        init();

        log.info("role inscrit");
    }

    public void listAllPermissions() {
        listOfUserPermissions = rolesPermissionsServices.findAllForRolesAndPerm(usersBean.getUsersEntity().getRolesByIdRoles().getId());
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

    /**
     * check permission user with string
     *
     * @param permission String
     * @return boolean
     */
    public boolean checkPermissionUserWithString(String permission) {
        if (permission == null || listOfUserPermissions.isEmpty()) {
            return false;
        }
        for (RolesPermissionsEntity rp : listOfUserPermissions) {
            if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase(permission)) {
                return true;
            }
        }
        return false;
    }

    public void listPermissionsUser() {
        if (!listOfUserPermissions.isEmpty()) {
            for (RolesPermissionsEntity rp : listOfUserPermissions) {
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_ads")) {
                    createAds = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_ads")) {
                    updateAds = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_ads")) {
                    deleteAds = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_ads")) {
                    readAds = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_users")) {
                    createUsers = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_users")) {
                    updateUsers = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_users")) {
                    deleteUsers = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_users")) {
                    readUsers = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_orders")) {
                    createOrders = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_orders")) {
                    updateOrders = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_orders")) {
                    deleteOrders = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_orders")) {
                    readOrders = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_roles")) {
                    createRoles = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_roles")) {
                    updateRoles = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_roles")) {
                    deleteRoles = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_roles")) {
                    readRoles = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_models")) {
                    createModels = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_models")) {
                    updateModels = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_models")) {
                    deleteModels = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_models")) {
                    readModels = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_options")) {
                    createOptions = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_options")) {
                    updateOptions = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_options")) {
                    deleteOptions = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_options")) {
                    readOptions = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_colors")) {
                    createColors = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_colors")) {
                    updateColors = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_colors")) {
                    deleteColors = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_colors")) {
                    readColors = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("create_insurances")) {
                    createInsurances = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("update_insurances")) {
                    updateInsurances = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("delete_insurances")) {
                    deleteInsurances = true;
                }
                if (rp.getPermissionsByIdPermissions().getLabel().equalsIgnoreCase("read_insurances")) {
                    readInsurances = true;
                }

            }
        }
    }

    public void setAddRolesPermissionsEntity(boolean createRolesPermissionsEntity) {
        this.addRolesPermissionsEntity = createRolesPermissionsEntity;
    }

    public List<RolesPermissionsEntity> getListOfUserPermissions() {
        return listOfUserPermissions;
    }

    public void setListOfUserPermissions(List<RolesPermissionsEntity> listOfUserPermissions) {
        this.listOfUserPermissions = listOfUserPermissions;
    }

    public boolean isUpdateUsers() {
        return updateUsers;
    }

    public void setUpdateUsers(boolean updateUsers) {
        this.updateUsers = updateUsers;
    }

    public boolean isCreateUsers() {
        return createUsers;
    }

    public void setCreateUsers(boolean createUsers) {
        this.createUsers = createUsers;
    }

    public boolean isDeleteUsers() {
        return deleteUsers;
    }

    public void setDeleteUsers(boolean deleteUsers) {
        this.deleteUsers = deleteUsers;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isReadUsers() {
        return readUsers;
    }

    public void setReadUsers(boolean readUsers) {
        this.readUsers = readUsers;
    }

    public boolean isCreateAds() {
        return createAds;
    }

    public void setCreateAds(boolean createAds) {
        this.createAds = createAds;
    }

    public boolean isUpdateAds() {
        return updateAds;
    }

    public void setUpdateAds(boolean updateAds) {
        this.updateAds = updateAds;
    }

    public boolean isDeleteAds() {
        return deleteAds;
    }

    public void setDeleteAds(boolean deleteAds) {
        this.deleteAds = deleteAds;
    }

    public boolean isReadAds() {
        return readAds;
    }

    public void setReadAds(boolean readAds) {
        this.readAds = readAds;
    }

    public boolean isReadColors() {
        return readColors;
    }

    public void setReadColors(boolean readColors) {
        this.readColors = readColors;
    }

    public boolean isDeleteColors() {
        return deleteColors;
    }

    public void setDeleteColors(boolean deleteColors) {
        this.deleteColors = deleteColors;
    }

    public boolean isAddColors() {
        return createColors;
    }

    public void setAddColors(boolean createColors) {
        this.createColors = createColors;
    }

    public boolean isUpdateInsurances() {
        return updateInsurances;
    }

    public void setUpdateInsurances(boolean updateInsurances) {
        this.updateInsurances = updateInsurances;
    }

    public boolean isReadInsurances() {
        return readInsurances;
    }

    public void setReadInsurances(boolean readInsurances) {
        this.readInsurances = readInsurances;
    }

    public boolean isDeleteInsurances() {
        return deleteInsurances;
    }

    public void setDeleteInsurances(boolean deleteInsurances) {
        this.deleteInsurances = deleteInsurances;
    }

    public boolean isAddInsurances() {
        return createInsurances;
    }

    public void setAddInsurances(boolean createInsurances) {
        this.createInsurances = createInsurances;
    }

    public boolean isUpdateOrders() {
        return updateOrders;
    }

    public void setUpdateOrders(boolean updateOrders) {
        this.updateOrders = updateOrders;
    }

    public boolean isReadOrders() {
        return readOrders;
    }

    public void setReadOrders(boolean readOrders) {
        this.readOrders = readOrders;
    }

    public boolean isDeleteOrders() {
        return deleteOrders;
    }

    public void setDeleteOrders(boolean deleteOrders) {
        this.deleteOrders = deleteOrders;
    }

    public boolean isUpdateOptions() {
        return updateOptions;
    }

    public void setUpdateOptions(boolean updateOptions) {
        this.updateOptions = updateOptions;
    }

    public boolean isReadOptions() {
        return readOptions;
    }

    public void setReadOptions(boolean readOptions) {
        this.readOptions = readOptions;
    }

    public boolean isDeleteOptions() {
        return deleteOptions;
    }

    public void setDeleteOptions(boolean deleteOptions) {
        this.deleteOptions = deleteOptions;
    }

    public boolean isUpdateModels() {
        return updateModels;
    }

    public void setUpdateModels(boolean updateModels) {
        this.updateModels = updateModels;
    }

    public boolean isReadModels() {
        return readModels;
    }

    public void setReadModels(boolean readModels) {
        this.readModels = readModels;
    }

    public boolean isDeleteModels() {
        return deleteModels;
    }

    public void setDeleteModels(boolean deleteModels) {
        this.deleteModels = deleteModels;
    }

    public boolean isUpdateRoles() {
        return updateRoles;
    }

    public void setUpdateRoles(boolean updateRoles) {
        this.updateRoles = updateRoles;
    }

    public boolean isReadRoles() {
        return readRoles;
    }

    public void setReadRoles(boolean readRoles) {
        this.readRoles = readRoles;
    }

    public boolean isDeleteRoles() {
        return deleteRoles;
    }

    public void setDeleteRoles(boolean deleteRoles) {
        this.deleteRoles = deleteRoles;
    }

    public boolean isAddRoles() {
        return createRoles;
    }

    public void setAddRoles(boolean createRoles) {
        this.createRoles = createRoles;
    }

    public boolean isUpdateColors() {
        return updateColors;
    }

    public void setUpdateColors(boolean updateColors) {
        this.updateColors = updateColors;
    }

    public boolean isCreateOrders() {
        return createOrders;
    }

    public void setCreateOrders(boolean createOrders) {
        this.createOrders = createOrders;
    }

    public boolean isCreateOptions() {
        return createOptions;
    }

    public void setCreateOptions(boolean createOptions) {
        this.createOptions = createOptions;
    }

    public boolean isCreateModels() {
        return createModels;
    }

    public void setCreateModels(boolean createModels) {
        this.createModels = createModels;
    }
}





