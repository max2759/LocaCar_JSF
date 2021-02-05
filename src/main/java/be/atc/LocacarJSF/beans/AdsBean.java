package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named(value = "adsBean")
@ViewScoped
public class AdsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -6795998607327751632L;
    public static Logger log = Logger.getLogger(AdsBean.class);

    private AdsServices adsServices = new AdsServicesImpl();
    private AdsEntity adsEntity = new AdsEntity();
    private List<AdsEntity> adsEntities;

    private boolean showPopup;
    private boolean addAdsEntity;

    private String success;
    private String fail;


    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        adsEntities = adsServices.findAll();
    }

    public void initialisationFields() {
        success = "";
        fail = "";
    }

    /**
     * Ouvrir le popup d'edition ou d'ajout
     */
    public void showPopupModal() {
        log.info("Show PopupModal");
        showPopup = true;
        if (getParam("id") != null) {
            addAdsEntity = false;
            int idAds = parseInt(getParam("id"));
            adsEntity = adsServices.findById(idAds);
        } else {
            addAdsEntity = true;
            adsEntity = new AdsEntity();
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


    /////// Getters and Setters

    public AdsServices getAdsServices() {
        return adsServices;
    }

    public void setAdsServices(AdsServices adsServices) {
        this.adsServices = adsServices;
    }

    public AdsEntity getAdsEntity() {
        return adsEntity;
    }

    public void setAdsEntity(AdsEntity adsEntity) {
        this.adsEntity = adsEntity;
    }

    public List<AdsEntity> getAdsEntities() {
        return adsEntities;
    }

    public void setAdsEntities(List<AdsEntity> adsEntities) {
        this.adsEntities = adsEntities;
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

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public boolean isAddAdsEntity() {
        return addAdsEntity;
    }

    public void setAddAdsEntity(boolean addAdsEntity) {
        this.addAdsEntity = addAdsEntity;
    }

}
