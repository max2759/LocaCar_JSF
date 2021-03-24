package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.CitiesServices;
import be.atc.LocacarJSF.services.UsersServices;
import be.atc.LocacarJSF.services.UsersServicesImpl;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Larche Marie-ellise
 */
@Named(value = "usersBean")
@SessionScoped
public class UsersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(UsersBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private UsersEntity usersEntity;
    private final UsersServices usersServices = new UsersServicesImpl();
    private List<UsersEntity> usersEntities;

    private AddressesEntity addressesEntity;
    private RolesEntity rolesEntity;

    @Inject
    private AddressesBean addressesBean;
    @Inject
    private CitiesBean citiesBean;
    @Inject
    private RolesPermissionsBean rolesPermissionsBean;


    private boolean showPopup;
    private boolean showPopupAdd;
    private String success;
    private String fail;
    private boolean editUsersEntity;
    private boolean addUserEntity;
    private boolean connexion = false;
    @Inject
    private RolesBean rolesBean;
    private List<UsersEntity> empty;
    private String roleLabel;
    private int userIdConnect;

    private CitiesEntity citiesEntity;
    private CitiesServices citiesServices;

    private String user;
    private boolean connected;

    public String toPageAddUser() {
        return "register?faces-redirect=true";
    }

    public String toPageUserUpdateByUsers() {
        return "userUpdateByUsers";
    }

    public String toPageConnexion() {
        return "connexion?faces-redirect=true";
    }

    public String toPageLogOut() {
        return "doLogoutUser";
    }

    @PostConstruct
    public void postConstruct() {
        log.info("begin postConstruct, connexion = " + connexion);
        if (connexion) {
            usersEntity = findUserById(usersEntity.getId());
            // citiesEntity = citiesServices.findByUser(usersEntity.getId());

            //  usersEntity = findUserWithAdresses(usersEntity.getId());

        } else {
            usersEntity = new UsersEntity();
            init();
        }
    }

    public void init() {
        usersEntities = usersServices.findAll();
    }

    /**
     * initializations
     */
    public void initialisationFields() {
        success = "";
        fail = "";
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
///
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        // convertir bytes en hexadécimal
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println(s.toString());
        String hashPass = s.toString();

        return hashPass;

    }

    /**
     *
     * @return Strng redirect page
     * @throws NoSuchAlgorithmException
     */
    public String connexion() throws NoSuchAlgorithmException {

        FacesContext context = FacesContext.getCurrentInstance();

        String username = usersEntity.getUsername();
        String password = usersEntity.getPassword();

        String hashPass = hashPassword(password);

////
        UsersEntity usersByUsernameAndPassword = usersServices.findByUsernameAndPassword(username, hashPass);
        log.info(usersByUsernameAndPassword);
        log.info("username recup + pass:" + username + " " + hashPass);
        if (usersByUsernameAndPassword != null) {
            usersEntity = usersServices.findByOneUsername(usersEntity.getUsername());

            if (usersEntity.isActive()) {
                connexion = true;
                connected = true;
                //recupere l'user

                int idUser = usersEntity.getId();
                log.info(idUser);
                addressesEntity = addressesBean.findByUserId(idUser);
                int idCities = addressesEntity.getCitiesByIdCities().getId();
                citiesEntity = citiesBean.findById(idCities);

                log.info(addressesEntity.getStreet()); //ok
                log.info(usersEntity.getLastname());  //ok
                log.info(citiesEntity.getLabel());  //ok

                //je ne recoit tjrs pas dans ma vue !
                //  addressesBean.connect();

                roleLabel = usersEntity.getRolesByIdRoles().getLabel();
                userIdConnect = usersEntity.getId();

                rolesPermissionsBean.listAllPermissions();
                rolesPermissionsBean.listPermissionsUser();
                log.info(rolesPermissionsBean.isReadUsers());

                //              FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.user.welcome"), null));
                //     success = JsfUtils.returnMessage(getLocale(), "fxs.user.welcome");
                return "index?faces-redirect=true";


            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.user.badConnexion"), null));
                // fail = JsfUtils.returnMessage(getLocale(), "fxs.user.badConnexion");
                return "connexion?faces-redirect=true";
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.user.badConnexion"), null));
            //  fail = JsfUtils.returnMessage(getLocale(), "fxs.user.badConnexion");
            log.info("existe pas");
            return "connexion?faces-redirect=true";
        }


    }


    /**
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public void addUser() throws NoSuchAlgorithmException, ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        log.info("begin addUserBean");
        initialisationFields();

        if (connected && rolesPermissionsBean.isCreateUsers()) {
            //permet d'ajouter dans userList par un admin
            log.info("restart de la session");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            usersEntity.setId(0);
            //  usersEntity = new UsersEntity();
        }

        LocalDateTime currentDate = LocalDateTime.now();

        //begin check username
        List<UsersEntity> usernameCheck = usersServices.findByUsername(usersEntity.getUsername());

        if (usernameCheck == null || usernameCheck.isEmpty()) {
            String password = usersEntity.getPassword();
            boolean bool = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password);
            log.info(bool);

            if (bool) {
                String hashPass = hashPassword(password);

                usersEntity.setPassword(hashPass);
                usersEntity.setActive(true);
                usersEntity.setRegisterDate(currentDate);
                if (connexion) {
                    log.info(roleLabel);
                    if (rolesPermissionsBean.isCreateUsers()) {
                        usersEntity.setRolesByIdRoles(rolesBean.findById(rolesEntity.getId()));
                    }
                } else {
                    log.info("role non admin et user non co");
                    usersEntity.setRolesByIdRoles(rolesBean.findById(2));
                }

                log.info("first name: " + usersEntity.getFirstname());

                usersServices.add(usersEntity);
                log.info("inscription");


                if (usersServices.findByOneUsername(usersEntity.getUsername()) != null || usersServices.findByUsername(usersEntity.getUsername()).size() > 0) {
                    log.info("begin add address");
                    List<UsersEntity> userId = usersServices.findByUsername(usersEntity.getUsername());
                    log.info(userId.size());

                    log.info("id du nouvel utilisateur " + userId.get(0).getId());
                    int idUser = userId.get(0).getId();
                    int idCity = citiesBean.getCitiesEntity().getId();

                    log.info("ville du nouvel user (dans userbean) " + citiesBean.getCitiesEntity().getId()
                    );

                    if (userId.size() == 1) {
                        log.info("bein add addresse, id: " + idUser);
                        boolean add = addressesBean.validateAddresse();
                        log.info("addresse true ou false? " + add);
                        if (add) {
                            addressesBean.addAddresse(idUser, idCity);
                            log.info("end add addresse");
                        } else {
                            fail = JsfUtils.returnMessage(getLocale(), "fxs.user.errorPassword");
                        }
                    }

                    if (connected && rolesPermissionsBean.isCreateUsers()) {
                        usersEntity = usersServices.findById(userIdConnect);
                    } else {
                        //   String logOut = doLogoutUser();
                        log.info("log out");

                        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "connexion.xhtml");
                        connected = false;
                        initialisationFields();
                    }


                } else {
                    log.info("begin delete physic after error insert user");

                    deletePhysic();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.user.errorInsert"), null));
                    fail = JsfUtils.returnMessage(getLocale(), "fxs.user.errorInsert");
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.user.errorPassword"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.user.usernameError"), null));
        }

    }

    /**
     * deconnexion
     *
     * @return redirection page de connexion
     */
    public void doLogoutUser() {
        log.info("befin logOut");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usersEntity = new UsersEntity();
        connected = false;
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "connexion.xhtml");

    }

    /**
     * delete user logic whit boolean to 0
     */
    public void deleteUser() {
        log.info("begin deleteUser logic");

        int idUser = parseInt(getParam("id"));
        log.info(idUser);
        usersEntity = usersServices.findById(idUser);
        //faut appeler le service aprés
        usersEntity.setActive(false);
        usersServices.update(usersEntity);

        log.info("end deleteUser logic");
    }

    /**
     * active user logic with boolean to 1
     */
    public void activeUser() {
        log.info("begin deleteUser logic");

        int idUser = parseInt(getParam("id"));
        log.info(idUser);
        usersEntity = usersServices.findById(idUser);
        //faut appeler le service aprés
        usersEntity.setActive(true);
        usersServices.update(usersEntity);

        log.info("end deleteUser logic");
    }

    /**
     * delete physic (in the add if add addresse don't work)
     */
    public void deletePhysic() {
        //faut appeler le service apréssetActive(false);
        usersServices.delete(usersEntity.getId());
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            log.info("getParam(\"id\") != null");
            editUsersEntity = false;
            int idUsers = parseInt(getParam("id"));
            usersEntity = usersServices.findById(idUsers);
        } else {
            log.info("getParam(\"id\") == null");
            editUsersEntity = true;
            usersEntity = new UsersEntity();
        }
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModalAdd() {
        log.info("show popupModal add");
        showPopupAdd = true;
        addUserEntity = true;
        usersEntity = new UsersEntity();
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
     * Sauvegarde l'entité modifiée
     */
    public void saveEdit() throws NoSuchAlgorithmException {

        FacesContext context = FacesContext.getCurrentInstance();
        List<UsersEntity> usersEntitiesByLabel = usersServices.findByUsername(usersEntity.getUsername());

        log.info("Save edit");
        if ((usersEntitiesByLabel.isEmpty())) {
            functionUpdateUser();
        } else if ((usersEntitiesByLabel.size() == 1)) {
            UsersEntity ue = usersEntitiesByLabel.get(0);

            if (ue.getId() == usersEntity.getId()) {
                functionUpdateUser();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.users.errorAdd"), null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.users.errorAdd"), null));
        }

       init();
    }


    /**
     * Repetition code for update UserEntity
     */
    public void functionUpdateUser() throws NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        log.info(usersEntity.getRolesByIdRoles().getLabel());
        String password = usersEntity.getPassword();
        boolean bool = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password);

        if (bool) {
            String hashPass = hashPassword(password);

            usersEntity.setPassword(hashPass);

            usersServices.update(usersEntity);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successUpdate"), null));
            //  success = JsfUtils.returnMessage(getLocale(), "successUpdate");
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "failUpdate"), null));
            //     fail = JsfUtils.returnMessage(getLocale(), "failUpdate");
        }
    }

    public UsersEntity findUserById(int idUser) {
        return usersServices.findById(idUser);
    }

    public UsersEntity findUserWithAdresses(int idUser) {
        log.info("begin findUserWithAdress in userBean");
        return usersServices.findUserWithAddresses(idUser);
    }

    public void updateUserByAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        usersServices.update(usersEntity);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successUpdate"), null));
    }


    /**
     * Méthode pour retourner les paramètres récupéré
     *
     * @param name
     * @return
     */
    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    /////////////////////////
    /// getter and setter ///
    /////////////////////////


    public List<SelectItem> getUpdatesEntitiesSelectItems() {
        log.info("begin getRolesEntitiesSelectItems() + rolesEntities " + usersEntities);
        return usersEntities.stream().map(c -> new SelectItem(c.getId(), c.getUsername())).collect(Collectors.toList());
    }

    public CitiesEntity getCitiesEntity() {
        return citiesEntity;
    }

    public void setCitiesEntity(CitiesEntity citiesEntity) {
        this.citiesEntity = citiesEntity;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isAddUserEntity() {
        return addUserEntity;
    }

    public void setAddUserEntity(boolean addUserEntity) {
        this.addUserEntity = addUserEntity;
    }

    public boolean isShowPopupAdd() {
        return showPopupAdd;
    }

    public void setShowPopupAdd(boolean showPopupAdd) {
        this.showPopupAdd = showPopupAdd;
    }

    public boolean isConnexion() {
        return connexion;
    }

    public void setConnexion(boolean connexion) {
        this.connexion = connexion;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public List<UsersEntity> getUsersEntities() {
        return usersEntities;
    }

    public void setUsersEntities(List<UsersEntity> usersEntities) {
        this.usersEntities = usersEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public boolean isEditUsersEntity() {
        return editUsersEntity;
    }

    public void setEditUsersEntity(boolean editUserEntity) {
        this.editUsersEntity = editUsersEntity;
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

    //verif mdp, ...

    //formulaire : toutes les données vont vers le bean qui vont partir dans le service qui va faire un add,
    // qui va appeler le DAO et enregsitrer en db l'inscription.
    // C'est au niveau des validator qu'on check que l'user est pas un con


}
