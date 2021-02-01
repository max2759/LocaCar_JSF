package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.UsersServices;
import be.atc.LocacarJSF.services.UsersServicesImpl;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Named(value = "usersBean")
@SessionScoped
public class UsersBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(UsersBean.class);

    private UsersEntity usersEntity = new UsersEntity();
    private UsersServices usersServices = new UsersServicesImpl();
    private List<UsersEntity> usersEntities;


    private boolean showPopup;
    private String success;
    private String fail;
    private boolean editUsersEntity;

    public void init() {
        log.info("init() - start");
        usersEntities = usersServices.findAll();
        //  log.info("Initialization done : "+usersEntities != null ? usersEntities.size() : 0+" results found.");
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    public void connexion() {
        log.info("je suis dans le connexion");
    }

    public void addUser() throws ParseException {

        log.info("begin addUserBean");
        Date currentDate = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
        String cur = formater.format(currentDate);
        Date curDate = formater.parse(cur);
        System.out.println(formater.format(currentDate));

        log.info("date du jour " + curDate);

        usersEntity.setFirstname(usersEntity.getFirstname());
        usersEntity.setLastname(usersEntity.getLastname());
        usersEntity.setUsername(usersEntity.getUsername());
        usersEntity.setPassword(usersEntity.getPassword());
        usersEntity.setBirthdate(usersEntity.getBirthdate());
        usersEntity.setVatNumber(usersEntity.getVatNumber());
        usersEntity.setActive(true);
        usersEntity.setIdRoles(1);
        usersEntity.setRegisterDate(currentDate);

        log.info("first name: " + usersEntity.getFirstname());
        usersServices.add(usersEntity);
        log.info("inscription");
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            editUsersEntity = false;
            int idOptions = parseInt(getParam("id"));
            usersEntity = usersServices.findById(idOptions);
        } else {
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

    public void setEditUsersEntity(boolean addOptionEntity) {
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
