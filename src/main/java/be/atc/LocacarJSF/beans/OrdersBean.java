package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.classes.JavaMailUtil;
import be.atc.LocacarJSF.classes.PDFUtil;
import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.enums.EnumOrderStatut;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;
import be.atc.LocacarJSF.services.OrdersServices;
import be.atc.LocacarJSF.services.OrdersServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Younes - Arifi
 * Orders Bean
 */
@Named(value = "ordersBean")
@SessionScoped
public class OrdersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -5251107202124824837L;

    private OrdersEntity ordersEntity;
    private final OrdersServices ordersServices = new OrdersServicesImpl();
    private double priceOrder;
    private String requestOrdersList;
    private List<OrdersEntity> ordersEntities;
    private boolean showPopup;
    private int cptContracts;
    @Inject
    private ContractsBean contractsBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private AdsBean adsBean;

    /**
     * Method post construct
     */
    @PostConstruct
    public void init() {
        log.info("OrdersBean : Post Construct");
        fieldsInitialization();
        findOrderAndfindContracts();
    }

    /**
     * Find order : if not null, find all contracts if contract == leasing, find insurance contract !
     */
    public void findOrderAndfindContracts() {
        log.info("OrdersBean : findOrderAndfindContracts!");
        ordersEntity = findOrders_ByIdUsers_andStatusIsPending();
        if (ordersEntity != null) {
            contractsBean.findAllContracts(ordersEntity.getId());
            calculatePriceOrder();
            setCptContracts(contractsBean.countContractsByIdOrder(ordersEntity.getId()));
        }
    }

    /**
     * Initialization fields
     */
    public void fieldsInitialization() {
        log.info("OrdersBean : Field initialization !");

        ordersEntities = Collections.emptyList();
    }

    /**
     * Method initialization after Validation basket
     */
    protected void initializationAfterValidation() {
        ordersEntity = null;
        priceOrder = 0;
    }

    /**
     * Add shop, call functions
     */
    public void addShop() {
        FacesContext context = FacesContext.getCurrentInstance();

        log.info("OrdersBean : AddShop");

        int idAds = Integer.parseInt(getParam("adsId"));

        if (idAds == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopError"), null));
            return;
        }

        adsBean.getAdsId(idAds);

        init();
        checkIfOrdersEntityIsNullAndCreateOrders();

        if (contractsBean.createContract()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopError"), null));
        }
        findOrderAndfindContracts();
    }

    /**
     * Check if ordersEntity is null, if null then createOrders !
     */
    protected void checkIfOrdersEntityIsNullAndCreateOrders() {
        if (ordersEntity == null) {
            log.info("OrdersBean : Aucun Orders n'est trouvé");
            createOrders();
        }
    }

    /**
     * Add shop for : end leasing
     */
    public void addShopEndLeasing() {
        log.info("OrdersBean : addShopEndLeasing");
        FacesContext context = FacesContext.getCurrentInstance();
        int idContract = Integer.parseInt(getParam("idContract"));
        init();
        checkIfOrdersEntityIsNullAndCreateOrders();

        if (contractsBean.createContractEndLeasing(idContract)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopError"), null));
        }
        findOrderAndfindContracts();
    }

    /**
     * Method calculate Price Order
     */
    public void calculatePriceOrder() {
        log.info("OrdersBean : calculatePriceOrder!");
        this.priceOrder = 0;
        for (ContractsEntity c : contractsBean.getContractsEntities()
        ) {
            this.priceOrder += c.getFinalPrice();
        }
    }


    /**
     * Validate Order
     */
    public String validateOrder() throws Exception {
        log.info("OrdersBean :validateOrder");
        FacesContext context = FacesContext.getCurrentInstance();

        contractsBean.findAllContracts(ordersEntity.getId());
        if (contractsBean.getContractsEntities().isEmpty()) {
            log.info("ContractsEntities is empty");
            return "index";
        }
        boolean test = false;
        for (ContractsEntity contractEntity : contractsBean.getContractsEntities()) {

            log.info(contractEntity.getCarsByIdCars().isActive());
            if (contractEntity.getCarsByIdCars().isActive()) {
                test = true;
            } else {
                test = false;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.basket.vehiculeNotAvailable"), null));
                break;
            }
            if (contractEntity.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                ContractInsurancesServices contractInsurancesServices = new ContractInsurancesServicesImpl();
                ContractInsurancesEntity contractInsurancesEntity = contractInsurancesServices.findByIdContract(contractEntity.getId());
                if (contractInsurancesEntity.getInsurancesByIdInsurance().isActive()) {
                    test = true;
                } else {
                    test = false;
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.basket.errorInsurance"), null));
                    break;
                }
            }
        }
        if (test) {
            log.info("Test ok ");
            ordersEntity.setOrderStatut(EnumOrderStatut.Validate);
            ordersEntity.setOrderDate(getDate());

            contractsBean.validateContractsOrder();

            ordersServices.update(ordersEntity);

            generatePDF();
            JavaMailUtil.sendMail(ordersEntity);

            initializationAfterValidation();
            contractsBean.initializationAfterValidation();
            setCptContracts(0);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "validateOrder.success"), null));
        }
        return "orderValidate";
    }

    /**
     * Find Orders by idUser, username or idOrder
     */
    public void findOrderCanceledOrValidate() {
        log.info("OrdersBean : findOrderCanceledOrValidate");

        FacesContext context = FacesContext.getCurrentInstance();

        log.info("requestOrdersList : " + requestOrdersList);
        if ((requestOrdersList.equals("")) || (!findOrdersCanceledOrValidateByIdOrder()) && (!findOrdersCanceledOrValidateByIdUser()) && (!findOrdersCanceledOrValidateByUsername())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fxs.ordersList.requestError"), null));
            ordersEntities = Collections.emptyList();
        }
    }

    /**
     * List all my orders.
     */
    public void findAllMyOrders() {
        log.info("OrdersBean : findAllMyOrders");
        FacesContext context = FacesContext.getCurrentInstance();

        ordersEntities = ordersServices.findAllByIdUsersAndStatusIsValidateOrCanceled(usersBean.getUsersEntity().getId());
        if (ordersEntities.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.modalContractsOrder.listOrderEmpty"), null));
        } else {
            deadlineLeasing();
        }
    }

    /**
     * check if a leasing expires
     */
    protected void deadlineLeasing() {
        log.info("OrdersBean : deadlineLeasing");
        List<OrdersEntity> ordersEntitiesDeadline = ordersServices.findAllOrdersByIdUserAndStatusIsValidate(usersBean.getUsersEntity().getId());
        if (!ordersEntitiesDeadline.isEmpty()) {
            contractsBean.findAllContractsInAllMyOrdersForLeasingAndDeadlineIsLowerThan1Month(ordersEntitiesDeadline);
        }
    }

    /**
     * Open popup when click detail
     */
    public void showPopupModal() {
        log.info("OrdersBean : showPopupModal");
        showPopup = true;
        if (getParam("idOrder") != null) {
            int idOrder = parseInt(getParam("idOrder"));
            ordersEntity = ordersServices.findById(idOrder);
            contractsBean.findAllContractsWhenFindOrders(ordersEntity.getId());
        }
    }

    /**
     * Close popup
     */
    public void hidePopupModal() {
        log.info("OrdersBean : hidePopupModal");
        showPopup = false;
    }

    /**
     * Delete order
     */
    public void deleteOrder() {
        log.info("OrdersBean : deleteOrder");
        FacesContext context = FacesContext.getCurrentInstance();

        int idOrder = parseInt(getParam("idOrder"));
        OrdersEntity ordersEntityToDelete = ordersServices.findById(idOrder);
        if (ordersEntityToDelete != null) {
            ordersEntityToDelete.setOrderStatut(EnumOrderStatut.Canceled);
            ordersServices.update(ordersEntityToDelete);
            findOrderCanceledOrValidate();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "fxs.ordersList.deleteSuccess"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "fail"), null));
        }
    }

    protected boolean findOrdersCanceledOrValidateByIdOrder() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByIdOrder");
        try {
            int idOrder = Integer.parseInt(requestOrdersList);
            ordersEntities = ordersServices.findAllByIdOrderAndStatusIsValidateOrCanceled(idOrder);
            return !ordersEntities.isEmpty();
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    protected boolean findOrdersCanceledOrValidateByIdUser() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByIdUser");
        try {
            int idUserTemp = Integer.parseInt(requestOrdersList);
            ordersEntities = ordersServices.findAllByIdUsersAndStatusIsValidateOrCanceled(idUserTemp);
            return !ordersEntities.isEmpty();
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    protected boolean findOrdersCanceledOrValidateByUsername() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByUsername");
        ordersEntities = ordersServices.findAllByUsernameUsersAndStatusIsValidateOrCanceled(requestOrdersList);
        return !ordersEntities.isEmpty();
    }

    /**
     * Find orders By IdUsers and status is pending
     *
     * @return OrdersEntity or null
     */
    protected OrdersEntity findOrders_ByIdUsers_andStatusIsPending() {
        log.info("OrdersBean : findOrders_ByIdUsers_andStatusIsPending!");
        return ordersServices.findByIdUsersAndStatusIsPending(usersBean.getUsersEntity().getId());
    }


    /**
     * Create Order !
     */
    protected boolean createOrders() {
        log.info("OrdersBean : createOrders!");
        ordersEntity = new OrdersEntity();
        ordersEntity.setUsersByIdUsers(usersBean.getUsersEntity());
        ordersEntity.setOrderDate(getDate());
        ordersEntity.setOrderStatut(EnumOrderStatut.Pending);
        return ordersServices.add(ordersEntity);
    }

    protected void generatePDF() {
        PDFUtil.generatePDF(ordersEntity, contractsBean.getContractsEntities(), priceOrder);
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(double priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getRequestOrdersList() {
        return requestOrdersList;
    }

    public void setRequestOrdersList(String requestOrdersList) {
        this.requestOrdersList = requestOrdersList;
    }

    public List<OrdersEntity> getOrdersEntities() {
        return ordersEntities;
    }

    public void setOrdersEntities(List<OrdersEntity> ordersEntities) {
        this.ordersEntities = ordersEntities;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public int getCptContracts() {
        return cptContracts;
    }

    public void setCptContracts(int cptContracts) {
        this.cptContracts = cptContracts;
    }
}
