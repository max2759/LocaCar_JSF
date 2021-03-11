package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import be.atc.LocacarJSF.dao.entities.RolesEntity;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.UsersServices;
import be.atc.LocacarJSF.services.UsersServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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

@Named(value = "usersBean")
@SessionScoped
public class UsersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(UsersBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private UsersEntity usersEntity;
    private UsersServices usersServices = new UsersServicesImpl();
    private List<UsersEntity> usersEntities;

    private AddressesEntity addressesEntity;
    private RolesEntity rolesEntity;

    @Inject
    private AddressesBean addressesBean;
    @Inject
    private CitiesBean citiesBean;


    private boolean showPopup;
    private String success;
    private String fail;
    private boolean editUsersEntity;
    private boolean addUserEntity;
    private boolean connexion = false;
    @Inject
    private RolesBean rolesBean;
    private List<UsersEntity> empty;

    private String user;
    private boolean connected;

    public String toPageAddUser() {
        return "addUser";
    }
    public String toPageConnexion() {
        return "connexion";
    }
    public String toPageLogOut() {
        return "doLogoutUser";
    }

    @PostConstruct
    public void postConstruct() {
        log.info("begin postConstruct, connexion = " + connexion);
        if (connexion == true) {
            log.info("connexion = true? :" + connexion);
            usersEntity = findUserById(usersEntity.getId());
        } else {
            log.info("connexion = false? : " + connexion);
            usersEntity = new UsersEntity();
            init();
        }
    }


    public void init() {
        log.info("init() - start");
        usersEntities = usersServices.findAll();
        //  log.info("Initialization done : "+usersEntities != null ? usersEntities.size() : 0+" results found.");
    }


    public void initialisationFields() {
        success = "";
        fail = "";
    }

    public void connexion() throws NoSuchAlgorithmException {

        String username = usersEntity.getUsername();
        String password = usersEntity.getPassword();
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

////
        UsersEntity usersByUsernameAndPassword = usersServices.findByUsernameAndPassword(username, hashPass);
        log.info(usersByUsernameAndPassword);
        log.info("username recup + pass:" + username + " " + hashPass   );
        if (usersByUsernameAndPassword != null) {
            connexion = true;
            connected = true;
            //recupere l'user
            usersEntity = usersServices.findByOneUsername(usersEntity.getUsername());
            int idUser = usersEntity.getId();
            log.info(idUser);
            addressesEntity = addressesBean.findByUserId(idUser);

            log.info(addressesEntity.getStreet()); //ok
            log.info(usersEntity.getLastname());  //ok

            //je ne recoit tjrs pas dans ma vue !
            addressesBean.connect();

            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");

            success = JsfUtils.returnMessage(getLocale(), "fxs.user.welcome");

            log.info("existe");
        } else {
            fail = JsfUtils.returnMessage(getLocale(), "fxs.user.badConnexion");
            log.info("existe pas");
        }

        log.info("je suis dans le connexion");

    }




    /**
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public void addUser() throws NoSuchAlgorithmException, ParseException {

        log.info("begin addUserBean");

        LocalDateTime currentDate = LocalDateTime.now();

        //begin check username
        List<UsersEntity> usernameCheck = usersServices.findByUsername(usersEntity.getUsername());
        log.info("usernameCheck est null == vide sinonb l'username existe deja= "+usernameCheck);

        if(usernameCheck == null || usernameCheck.isEmpty() ){

            String password = usersEntity.getPassword();
            boolean bool = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", password);
            log.info(bool);

            if(bool == true){
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                // convertir bytes en hexadécimal
                StringBuilder s = new StringBuilder();
                for (byte b : hash) {
                    s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                }
                System.out.println(s.toString());
                String hashPass = s.toString();

                log.info("mdp: " + hash);

                usersEntity.setPassword(hashPass);
                usersEntity.setActive(true);
                usersEntity.setRegisterDate(currentDate);
                usersEntity.setRolesByIdRoles(rolesBean.findById(1));

                log.info("first name: " + usersEntity.getFirstname());

                usersServices.add(usersEntity);
                log.info("inscription");



                if(usersServices.findByOneUsername(usersEntity.getUsername()) != null || usersServices.findByUsername(usersEntity.getUsername()).size() > 0){
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
                        addressesBean.addAddresse(idUser, idCity);
                        log.info("end add addresse");
                    }

                 //   String logOut = doLogoutUser();
                    log.info("log out");

                    FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "connexion.xhtml");
                    connected = false;
            }else{
                    log.info("begin delete physic after error insert user");

                    deletePhysic();
                    fail = JsfUtils.returnMessage(getLocale(), "fxs.user.errorInsert");
                }

            }else{
                fail = JsfUtils.returnMessage(getLocale(), "fxs.user.errorPassword");
            }
        }else{
            fail = JsfUtils.returnMessage(getLocale(), "fxs.user.usernameError");
        }

    }

    public String doLogoutUser(){
        log.info("befin logOut");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usersEntity = new UsersEntity();
        connected = false;
        return "successLogOut";
    }

    public void deleteUser() {
        log.info("begin deleteUser logic");

        //faut appeler le service aprés
        usersEntity.setActive(false);
        usersServices.update(usersEntity);

        log.info("end deleteUser logic");


    }

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



    public void showPopupModalUpdateByUser() {
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
     * Fermer le popup d'edition ou d'ajout
     */
    public void hidePopupModal() {
        log.info("Hide PopupModal");
        initialisationFields();
        showPopup = false;
    }


    /**
     * Sauvegarde l'entité modifiée
     */
    public void saveEdit() {

        List<UsersEntity> usersEntitiesByLabel = usersServices.findByUsername(usersEntity.getUsername());


        log.info("Save edit");
        if ((usersEntitiesByLabel.isEmpty())) {
            functionUpdateUser();
        } else if ((usersEntitiesByLabel.size() == 1)) {
            UsersEntity ue = usersEntitiesByLabel.get(0);

            if (ue.getId() == usersEntity.getId()) {
                functionUpdateUser();
            } else {
                fail = JsfUtils.returnMessage(locale, "fxs.users.errorAdd");
            }
        } else {
            fail = JsfUtils.returnMessage(locale, "fxs.users.errorAdd");
        }

        init();
    }

    /**
     * Repetition code for add userEntity
     */
    public void functionAddUser() {
        usersServices.add(usersEntity);
        success = JsfUtils.returnMessage(getLocale(), "fxs.users.succesAdd");

    }

    /**
     * Repetition code for update UserEntity
     */
    public void functionUpdateUser() {
        log.info(usersEntity.getRolesByIdRoles().getLabel());
        usersServices.update(usersEntity);
        success = JsfUtils.returnMessage(getLocale(), "successUpdate");
    }

    public UsersEntity findUserById(int idUser) {
        return usersServices.findById(idUser);
    }

    public List<UsersEntity> findUserWithAdresses(int idUser) {
        log.info("begin findUserWithAdress in userBean");
        return usersServices.findUserWithAddresses(idUser);
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
