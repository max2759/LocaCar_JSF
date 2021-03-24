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
    private List<AdsEntity> adsEntities;
    private List<AdsEntity> allDisabledAds;
    private List<AdsEntity> allAdsByLabelEntities;
    private CarsPicturesEntity carsPicturesEntity;
    private List<AdsEntity> allAdsByUser;
    private List<CarsOptionsEntity> carsOptionsEntityList;
    private List<String> imagePath = new ArrayList<>();

    private String folder = "resources/upload/";
    private Map<Integer, String> carsPicturesEntityMap = new HashMap<>();

    private List<CarsPicturesEntity> carsPicturesEntityList;
    private int idModelSearch;
    private double priceSearch;
    private EnumTypeAds typeAdsSearch;

    @Inject
    private PicturesBean picturesBean;

    @Inject
    private CarsBean carsBean;

    @Inject
    private CarsOptionsBean carsOptionsBean;

    @Inject
    private UsersBean usersBean;


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
    }


    public void initialisationFields() {
        success = "";
        fail = "";
    }

    /**
     * Open edition/add pop up
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
     * Close edition/add pop up
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
    public String addAds() throws ServletException, IOException {
        log.info("Sauvegarde");

        FacesContext context = FacesContext.getCurrentInstance();

        carsBean.addCar();
        adsEntity.setUsersByIdUsers(usersBean.getUsersEntity());
        adsEntity.setCarsByIdCars(carsBean.getCarsEntity());
        adsEntity.setDateStart(dateStart);
        adsEntity.setDateEnd(dateEnd);
        adsEntity.setActive(true);


        if (createAds()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "addAdsSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addAdsError"), null));
        }
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "adsDetails?faces-redirect=true" + "adsId=" + adsEntity.getId();
    }

    public void displayAllActiveAds() {
        adsEntities = adsServices.findAll();
        paginator = new RepeatPaginator(adsEntities);
        findOnePicturesByIdCars();
    }

    /**
     * Find all ads with search string
     */
    public void allAdsByLabel() {
        log.info("AdsBean : allAdsByLabel");

        FacesContext context = FacesContext.getCurrentInstance();

        if (searchString.equals("") || (!findAdsByLabel())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
            adsEntities = Collections.emptyList();
        }

        adsEntities = adsServices.findByLabel(searchString);
        paginator = new RepeatPaginator(adsEntities);
    }

    /**
     * Find all ads by user ID
     */
    public void findAllAdsByUsers() {
        int idUser = usersBean.getUsersEntity().getId();
        allAdsByUser = adsServices.findAdsByIdUser(idUser);
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
     * Display disabled ads by user ID
     */
    public void displayDisabeldAdsByUser() {
        log.info("AdsBean : displayDisabeldAdsByUser");
        int idUser = usersBean.getUsersEntity().getId();
        allDisabledAds = adsServices.findDisabledAdsByUser(idUser);
    }


    /**
     * Update ads entity
     *
     * @return true or false
     */
    public boolean updateAds() {
        return adsServices.update(adsEntity);
    }

    /**
     * Update ads and then redirect to adsDetails page
     *
     * @return page adsDetails.xhtml
     * @throws ServletException
     * @throws IOException
     */
    public String updateAddedAds() throws ServletException, IOException {
        log.info("Update" + adsEntity);

        FacesContext context = FacesContext.getCurrentInstance();

        carsBean.updateAddedCar(adsEntity.getCarsByIdCars());

        if (updateAds()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "updateAdsSucces"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "addAdsError"), null));
        }
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "adsDetails?faces-redirect=true" + "adsId=" + adsEntity.getId();

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
            findAllAdsByUsers();

        } else {
            adsEntity.setActive(true);
            adsEntity.setDateStart(dateStart);
            adsEntity.setDateEnd(dateEnd);
            adsEntity.getCarsByIdCars().setActive(true);
            carsBean.updateCar(adsEntity.getCarsByIdCars());
            updateAds();
            displayDisabeldAdsByUser();
        }
    }

    /**
     * Return list of car options by carsID
     */
    public void getAllCarOptions() {

        int idCars = adsEntity.getCarsByIdCars().getId();
        carsOptionsEntityList = carsOptionsBean.findCarsOptionsByCarsId(idCars);
        carsOptionsBean.findAllCarOptionByIdCar(idCars);
    }

    /**
     * Return one ads by its id
     *
     * @param id
     */
    public void getAdsId(int id) {
        adsEntity = adsServices.findById(id);
    }

    /**
     * Return result of search by model, type ads and price or combination
     */
    public void advancedSearch() {

        log.info("Début advanceSearch" + idModelSearch + typeAdsSearch + priceSearch);

        FacesContext context = FacesContext.getCurrentInstance();

        if (idModelSearch != 0 && typeAdsSearch != null && priceSearch != 0) {
            adsEntities = adsServices.findAdsByModelsAndPriceAndTypeAds(typeAdsSearch, idModelSearch, priceSearch);
            if (adsEntities.isEmpty()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
            }
            paginator = new RepeatPaginator(adsEntities);
        } else {
            if (idModelSearch != 0) {
                if (typeAdsSearch != null) {
                    if (priceSearch == 0) {
                        adsEntities = adsServices.findAdsByModelAndTypeAds(typeAdsSearch, idModelSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                    }
                } else {
                    if (priceSearch != 0) {
                        adsEntities = adsServices.findAdsByModelsAndPrice(idModelSearch, priceSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    } else {
                        adsEntities = adsServices.findAdsByModels(idModelSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    }
                }
            } else {
                if (typeAdsSearch != null) {
                    if (priceSearch != 0) {
                        adsEntities = adsServices.findAdsByPriceAndTypeAds(typeAdsSearch, priceSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    } else {
                        adsEntities = adsServices.findAdsByTypeAds(typeAdsSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    }

                } else {
                    if (priceSearch != 0) {
                        adsEntities = adsServices.findAdsByPrice(priceSearch);
                        if (adsEntities.isEmpty()) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                        }
                        paginator = new RepeatPaginator(adsEntities);
                    } else {
                        adsEntities = Collections.emptyList();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.allads.searchError"), null));
                    }
                }
            }
        }

    }

    /**
     * Get one picture from its cars id
     *
     */
    protected void findOnePicturesByIdCars() {
        for (AdsEntity a : adsEntities
        ) {
            try {
                CarsPicturesEntity cp = picturesBean.findCarsPicturesByIdCars(a.getCarsByIdCars().getId()).get(0);
                carsPicturesEntityMap.put(a.getCarsByIdCars().getId(), cp.getLabel());
            } catch (Exception e) {
                e.getMessage();
            }
        }

        log.info(carsPicturesEntityMap);

    }

    /**
     * Return page allads.xhtml
     *
     * @return String
     */
    public String toPageAllAds() {
        return "allads?faces-redirect=true";
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

    public int getIdModelSearch() {
        return idModelSearch;
    }

    public void setIdModelSearch(int idModelSearch) {
        this.idModelSearch = idModelSearch;
    }

    public double getPriceSearch() {
        return priceSearch;
    }

    public void setPriceSearch(double priceSearch) {
        this.priceSearch = priceSearch;
    }

    public List<AdsEntity> getAllAdsByUser() {
        return allAdsByUser;
    }

    public void setAllAdsByUser(List<AdsEntity> allAdsByUser) {
        this.allAdsByUser = allAdsByUser;
    }

    public CarsPicturesEntity getCarsPicturesEntity() {
        return carsPicturesEntity;
    }

    public void setCarsPicturesEntity(CarsPicturesEntity carsPicturesEntity) {
        this.carsPicturesEntity = carsPicturesEntity;
    }

    public EnumTypeAds getTypeAdsSearch() {
        return typeAdsSearch;
    }

    public void setTypeAdsSearch(EnumTypeAds typeAdsSearch) {
        this.typeAdsSearch = typeAdsSearch;
    }

    public Map<Integer, String> getCarsPicturesEntityMap() {
        return carsPicturesEntityMap;
    }

    public void setCarsPicturesEntityMap(Map<Integer, String> carsPicturesEntityMap) {
        this.carsPicturesEntityMap = carsPicturesEntityMap;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
