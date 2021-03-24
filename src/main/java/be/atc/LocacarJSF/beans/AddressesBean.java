package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.services.AddressesServices;
import be.atc.LocacarJSF.services.AddressesServicesImpl;
import org.apache.log4j.Logger;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
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

/**
 * @author Larche Marie-ellise
 */
@Named(value = "addressesBean")
@SessionScoped
public class AddressesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(AddressesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private AddressesEntity addressesEntity;
    private AddressesServices addressesServices = new AddressesServicesImpl();
    private List<AddressesEntity> addressesEntities;

    private UsersEntity usersEntity;

    @Inject
    private CitiesBean citiesBean;

    private CitiesEntity citiesEntity;

    private boolean showPopup;
    private String success;
    private String fail;
    private int userId;
    private boolean editAddressesEntity;
    private boolean addAddresseEntity;
    @Inject
    private UsersBean usersBean;

    private boolean update;


    @PostConstruct
    public void postContruct() {
        userId = usersBean.getUsersEntity().getId();
        log.info("begin postConstruct, addressebean = " + userId);
        if (userId != 0) {
            log.info("connexion = true? :" + userId);
            addressesEntity = findByUserId(userId);

        } else {
            log.info("connexion = false? : " + userId);
            addressesEntity = new AddressesEntity();
            init();
        }
    }

    public void init() {

        log.info("init in adressesbean");
        addressesEntities = addressesServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    /**
     * adresse entity for user co
     */
    public void connect() {
        if (usersBean.isConnected() == true) {
            userId = usersBean.getUsersEntity().getId();
            usersEntity = usersBean.findUserById(userId);
            log.info("connecté: recup des données, userId = " + usersEntity.getId());
            addressesEntity = addressesServices.findByIdUser(usersEntity.getId());
        } else {
            log.info("pas connecté, pas de recup d'adresses?");
        }
    }

    /**
     * @param idUser
     * @param idCity
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public void addAddresse(int idUser, int idCity) throws ParseException, NoSuchAlgorithmException {
        log.info("begin addAddresseBean");

        addressesEntity.setUsersByIdUsers(usersBean.findUserById(idUser));

        log.info("label recu du form: " + addressesEntity.getStreet());
        //log.info("id de la ville: "+ citiesEntity.getId());

        addressesEntity.setCitiesByIdCities(citiesBean.findById(idCity));
        addressesServices.add(addressesEntity);
        log.info("addresse inscrit");
    }


    public boolean validateAddresse() {
        String streetVer = addressesEntity.getStreet();
        log.info(streetVer);
        boolean add = false;
        if (streetVer != null) {
            add = true;
        }
        log.info(add);
        return add;
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            log.info("getParam(\"id\") != null");
            editAddressesEntity = false;
            int idUsers = parseInt(getParam("id"));
            addressesEntity = addressesServices.findByIdUser(idUsers);
        } else {
            log.info("getParam(\"id\") == null");
            editAddressesEntity = true;
            addressesEntity = new AddressesEntity();
        }
    }

    public void hidePopupModal() {
        log.info("Hide PopupModal");
        initialisationFields();
        showPopup = false;
    }

    /**
     * Sauvegarde l'entité modifiée
     */
    public void saveEdit() {
        log.info("begin -save edit addresse");
        //userId = usersBean.getUsersEntity().getId();
        //addressesEntity = addressesServices.findByIdUser(userId);

        log.info(addressesEntity);
        log.info(addressesEntity.getNumber());
        int idCity = citiesBean.getCitiesEntity().getId();
        log.info(idCity);

        if (idCity != 0) {
            addressesEntity.setCitiesByIdCities(citiesBean.findById(idCity));
            functionUpdateAddresse();
            update = true;
        } else if (addressesEntity != null && update == false) {
            functionUpdateAddresse();
        } else {
            fail = JsfUtils.returnMessage(locale, "fxs.users.errorAdd");
        }

        log.info("Save edit");


        init();
    }

    /**
     * Repetition code for update AddresseEntity
     */
    public void functionUpdateAddresse() {
        log.info("begin - updateAddresse");
        log.info(addressesEntity.getNumber());
        log.info(addressesEntity.getCitiesByIdCities().getId());


        log.info(citiesBean.getCitiesEntity().getId());
        int idCity = citiesBean.getCitiesEntity().getId();
        if (idCity != 0) {
            addressesEntity.setCitiesByIdCities(citiesBean.findById(idCity));

            addressesServices.update(addressesEntity);

        } else {
            success = JsfUtils.returnMessage(locale, "fxs.users.idCity0");

        }


        log.info("end updateaddresse");
    }


    public AddressesEntity findById(int id) {
        return addressesServices.findById(id);
    }

    public AddressesEntity findByUserId(int idUser) {
        return addressesServices.findByIdUser(usersBean.getUsersEntity().getId());
    }


    public String getParam(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(name);
    }


    ///////////////////////////////////////
    ///// getters and setter //////////////
    //////////////////////////////////////


    public AddressesEntity getAddressesEntity() {
        return addressesEntity;
    }

    public void setAddressesEntity(AddressesEntity addressesEntity) {
        this.addressesEntity = addressesEntity;
    }

    public AddressesServices getAddressesServices() {
        return addressesServices;
    }

    public void setAddressesServices(AddressesServices addressesServices) {
        this.addressesServices = addressesServices;
    }

    public List<AddressesEntity> getAddressesEntities() {
        return addressesEntities;
    }

    public void setAddressesEntities(List<AddressesEntity> addressesEntities) {
        this.addressesEntities = addressesEntities;
    }

    public boolean isEditAddressesEntity() {
        return editAddressesEntity;
    }

    public void setEditAddressesEntity(boolean editAddressesEntity) {
        this.editAddressesEntity = editAddressesEntity;
    }

    public boolean isAddAddresseEntity() {
        return addAddresseEntity;
    }

    public void setAddAddresseEntity(boolean addAddresseEntity) {
        this.addAddresseEntity = addAddresseEntity;
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
