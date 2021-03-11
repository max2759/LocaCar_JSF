package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.AdsServices;
import be.atc.LocacarJSF.services.AdsServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author Maximilien - Zabbara
 */
@Named(value = "adsBean")
@ViewScoped
public class AdsBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = -6795998607327751632L;

    private LocalDateTime dateStart = LocalDateTime.now();
    private LocalDateTime dateEnd = dateStart.plusMonths(1);

    private AdsEntity adsEntity;
    private AdsServices adsServices = new AdsServicesImpl();
    private RepeatPaginator paginator;
    Map<Integer, List<CarsPicturesEntity>> carsPicturesMap = new HashMap<>();
    private List<AdsEntity> adsEntities;
    private List<AdsEntity> allDisabledAds;
    private List<AdsEntity> allAdsByLabelEntities;

    private List<CarsPicturesEntity> carsPicturesEntityList;
    private String page;
    @Inject
    private PicturesBean picturesBean;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


    @Inject
    private CarsBean carsBean;

    public String toPageAdDetails() {
        return "adsDetails";
    }

    private boolean showPopup;
    private boolean addAdsEntity;

    private String success;
    private String fail;
    private String searchString;

    /**
     * PostConstruct : appelé après le constructeur.
     * Met à jour la liste optionsEntities
     */
    @PostConstruct
    public void init() {
        adsEntity = new AdsEntity();
        adsEntities = adsServices.findAll();
        allDisabledAds = adsServices.findAllDisabledAds();
        fieldsInitialization();
    }

    public void fieldsInitialization() {
        log.info("AdsBean : Field initialization !");
        allAdsByLabelEntities = Collections.emptyList();
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


    public void addAds() throws ServletException, IOException {
        log.info("Sauvegarde");

        FacesContext context = FacesContext.getCurrentInstance();

        boolean adsSucces = true;

        carsBean.addCar();
        adsEntity.setCarsByIdCars(carsBean.getCarsEntity());
        adsEntity.setDateStart(dateStart);
        adsEntity.setDateEnd(dateEnd);
        adsEntity.setActive(true);


        if (adsSucces) {
            adsSucces = createAds();
        }

        if (adsSucces) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "addAdsSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addAdsError"), null));

        }
        init();
    }


    /**
     * Find all ads by label
     */
    public void allAdsByLabel() {
        log.info("AdsBean : allAdsByLabel");

        FacesContext context = FacesContext.getCurrentInstance();

        if (searchString.equals("") || (!findAdsByLabel())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
            allAdsByLabelEntities = Collections.emptyList();
        }

        paginator = new RepeatPaginator(allAdsByLabelEntities);
    }

    /**
     * Récupérer l'annonce par son titre
     *
     * @return adsEntities
     */
    protected boolean findAdsByLabel() {
        log.info("AdsBean : findAdsByLabel");
        allAdsByLabelEntities = adsServices.findByLabel(searchString);
        return !allAdsByLabelEntities.isEmpty();
    }

    public void displayOneAd() {
        log.info("Adsbean : displayOneAd");

        int idAd = parseInt(getParam("adsId"));
        adsServices.findById(idAd);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "adsDetails.xhtml");
    }

    /**
     *
     */
    public void linkCarsPictureMapWithAds() {
        for (AdsEntity ads : adsEntities) {
            List<CarsPicturesEntity> carsPicturesEntityList = picturesBean.findCarsPicturesByIdCars(ads.getCarsByIdCars().getId());
            carsPicturesMap.put(ads.getId(), carsPicturesEntityList);
        }
    }

    /**
     * Update ads entity
     *
     * @return
     */
    public boolean updateAds() {
        return adsServices.update(adsEntity);
    }


    /**
     * Change status of ads to active or inactive
     */
    public void setAdsStatus() {
        adsEntity = adsServices.findById(Integer.parseInt(getParam("idAds")));

        if (adsEntity.isActive()) {
            adsEntity.setActive(false);
            adsEntity.getCarsByIdCars().setActive(false);
            carsBean.updateCar(adsEntity.getCarsByIdCars());
            updateAds();
            init();
        } else {
            adsEntity.setActive(true);
            adsEntity.setDateStart(dateStart);
            adsEntity.setDateEnd(dateEnd);
            adsEntity.getCarsByIdCars().setActive(true);
            carsBean.updateCar(adsEntity.getCarsByIdCars());
            updateAds();
            init();
        }
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

    public Map<Integer, List<CarsPicturesEntity>> getCarsPicturesMap() {
        return carsPicturesMap;
    }

    public void setCarsPicturesMap(Map<Integer, List<CarsPicturesEntity>> carsPicturesMap) {
        this.carsPicturesMap = carsPicturesMap;
    }

    public List<AdsEntity> getAllDisabledAds() {
        return allDisabledAds;
    }

    public void setAllDisabledAds(List<AdsEntity> allDisabledAds) {
        this.allDisabledAds = allDisabledAds;
    }
}
