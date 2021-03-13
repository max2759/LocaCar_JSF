package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
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
import java.util.*;

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
    private List<CarsOptionsEntity> carsOptionsEntityList;
    private List<String> imagePath = new ArrayList<>();
    //    private String folder = Constants.FILE_OUTPUT_IMAGE;
    private String folder = "resources/upload/";

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

    @Inject
    private CarsOptionsBean carsOptionsBean;


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
     * Get values of EnumTypes
     *
     * @return enumTypes
     */
    public EnumTypeAds[] getEnumTypes() {
        return EnumTypeAds.values();
    }

    /**
     * Call service method for adding an ads entity to DB
     *
     * @return true or false
     */
    public boolean createAds() {
        return adsServices.add(adsEntity);
    }


    /**
     * Add ads with form
     *
     * @throws ServletException
     * @throws IOException
     */
    public void addAds() throws ServletException, IOException {
        log.info("Sauvegarde");

        FacesContext context = FacesContext.getCurrentInstance();

        carsBean.addCar();
        adsEntity.setCarsByIdCars(carsBean.getCarsEntity());
        adsEntity.setDateStart(dateStart);
        adsEntity.setDateEnd(dateEnd);
        adsEntity.setActive(true);


        if (createAds()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "addAdsSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addAdsError"), null));
        }
        init();
    }


    /**
     * Find all ads with search string
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
     * Get list of ads by label enter in the search form
     *
     * @return adsEntities
     */
    protected boolean findAdsByLabel() {
        log.info("AdsBean : findAdsByLabel");

        allAdsByLabelEntities = adsServices.findByLabel(searchString);
        return !allAdsByLabelEntities.isEmpty();
    }

    /**
     * Get one ads by id
     */
    public void displayOneAd() {
        log.info("Adsbean : displayOneAd");

        int idAd = parseInt(getParam("adsId"));
        adsEntity = adsServices.findById(idAd);
        carsPicturesEntityList = picturesBean.findCarsPicturesByIdCars(adsEntity.getCarsByIdCars().getId());
        for (CarsPicturesEntity c : carsPicturesEntityList) {
            imagePath.add(folder + c.getLabel());
        }
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
     * @return true or false
     */
    public boolean updateAds() {
        return adsServices.update(adsEntity);
    }

    public void updateAddedAds() throws ServletException, IOException {
        log.info("Update");

        boolean updateSucces = true;

        FacesContext context = FacesContext.getCurrentInstance();

        carsBean.updateAddedCar(adsEntity.getCarsByIdCars());

        if (updateAds()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "addAdsSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addAdsError"), null));
        }


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

    /**
     * Return list of car options by carsID
     */
    public void getAllCarOptions() {

        int idCars = adsEntity.getCarsByIdCars().getId();
        carsOptionsEntityList = carsOptionsBean.findCarsOptionsByCarsId(idCars);
    }

    /**
     * Return one ads by its id
     *
     * @param id
     */
    public void getAdsId(int id) {
        adsEntity = adsServices.findById(id);
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

    public List<CarsOptionsEntity> getCarsOptionsEntityList() {
        return carsOptionsEntityList;
    }

    public void setCarsOptionsEntityList(List<CarsOptionsEntity> carsOptionsEntityList) {
        this.carsOptionsEntityList = carsOptionsEntityList;
    }

    public List<String> getImagePath() {
        return imagePath;
    }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }
}
