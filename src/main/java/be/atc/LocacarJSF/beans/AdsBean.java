package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named(value = "adsBean")
@SessionScoped
public class AdsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -6795998607327751632L;

    private LocalDateTime dateStart = LocalDateTime.now();

    /*
    LocalDateTime ldt = LocalDateTime.ofInstant(dateStart.toInstant(), ZoneId.systemDefault());
    Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    LocalDateTime outEnd = ldt.plusMonths(1);
    */
    //private LocalDateTime dateEnd = Date.from(outEnd.atZone(ZoneId.systemDefault()).toInstant());
    private LocalDateTime dateEnd = dateStart.plusMonths(1);

    private AdsEntity adsEntity;
    private AdsServices adsServices = new AdsServicesImpl();
    private List<AdsEntity> adsEntities;

    private List<CarsPicturesEntity> carsPicturesEntityList;

    @Inject
    private CarsBean carsBean;

    @Inject
    private PicturesBean picturesBean;

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
        for (AdsEntity img : adsEntities) {
            carsPicturesEntityList = picturesBean.findPictures(img.getCarsByIdCars().getId());
        }
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

    /**
     * Récupérer les valeurs de l'enum EnumTypeAds
     *
     * @return enumTypes
     */
    public EnumTypeAds[] getEnumTypes() {
        return EnumTypeAds.values();
    }

    /**
     * @return
     */
    public boolean createAds() {
        return adsServices.add(adsEntity);
    }

    public void addAds() {
        initialisationFields();
        log.info("Sauvegarde");

        adsEntity.setCarsByIdCars(carsBean.getCarsEntity());
        adsEntity.setDateStart(dateStart);
        adsEntity.setDateEnd(dateEnd);
        adsEntity.setActive(true);

        createAds();

        init();
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


    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<CarsPicturesEntity> getCarsPicturesEntityList() {
        return carsPicturesEntityList;
    }

    public void setCarsPicturesEntityList(List<CarsPicturesEntity> carsPicturesEntityList) {
        this.carsPicturesEntityList = carsPicturesEntityList;
    }
}
