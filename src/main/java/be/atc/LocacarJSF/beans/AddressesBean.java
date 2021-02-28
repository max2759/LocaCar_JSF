package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AddressesEntity;
import be.atc.LocacarJSF.dao.entities.CitiesEntity;
import be.atc.LocacarJSF.services.AddressesServices;
import be.atc.LocacarJSF.services.AddressesServicesImpl;
import org.apache.log4j.Logger;

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

@Named(value = "addressesBean")
@SessionScoped
public class AddressesBean implements Serializable {
    private static final long serialVersionUID = -8262263353009937764L;
    public static Logger log = Logger.getLogger(AddressesBean.class);
    Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private AddressesEntity addressesEntity;
    private AddressesServices addressesServices = new AddressesServicesImpl();
    private List<AddressesEntity> addressesEntities;

    @Inject
    private CitiesBean citiesBean;

    private CitiesEntity citiesEntity;

    private boolean showPopup;
    private String success;
    private String fail;
    private int userId = 0;
    private boolean editAddressesEntity;
    private boolean addAddresseEntity;
    @Inject
    private UsersBean usersBean;

    @PostConstruct
    public void postContruc() {
        log.info("begin postConstruct, connexion = " + userId);
        if (userId != 0) {
            log.info("connexion = true? :" + userId);
            findByUserId();
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


    public void addAddresse(int id) throws ParseException, NoSuchAlgorithmException {

        log.info("begin addAddresseBean");


        addressesEntity.setStreet(addressesEntity.getStreet());
        addressesEntity.setUsersByIdUsers(usersBean.findUserById(id));
        addressesEntity.setNumber(addressesEntity.getNumber());
        addressesEntity.setBox(addressesEntity.getBox());
        addressesEntity.setCitiesByIdCities(citiesBean.findById(1));

        log.info("label recu du form: " + addressesEntity.getStreet());
        addressesServices.add(addressesEntity);
        log.info("addresse inscrit");
    }


    public AddressesEntity findById(int id) {
        return addressesServices.findById(id);
    }

    public void findByUserId() {
        addressesEntities = addressesServices.findByIdUser(usersBean.getUsersEntity().getId());
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
