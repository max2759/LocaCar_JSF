package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.enums.EnumOrderStatut;
import be.atc.LocacarJSF.services.OrdersServices;
import be.atc.LocacarJSF.services.OrdersServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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

    // Remplacer par l'utilisateur
    private int idUser = 7;

    private OrdersEntity ordersEntity;
    private OrdersServices ordersServices = new OrdersServicesImpl();
    private double priceOrder;
    private String success;
    private String fail;
    private String requestOrdersList;
    private List<OrdersEntity> ordersEntities;
    private boolean showPopup;
    private int cptContracts;
    @Inject
    private ContractsBean contractsBean;
    @Inject
    private UsersBean usersBean;

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
        success = "";
        fail = "";
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
        log.info("OrdersBean : AddShop");
        init();
        if (ordersEntity == null) {
            log.info("OrdersBean : Aucun Orders n'est trouvé");
            createOrders();
        }

        if (contractsBean.createContract()) {
            fail = "";
            success = JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopSuccess");
        } else {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopError");
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
    public String validateOrder() {
        log.info("OrdersBean :validateOrder");
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
                break;
            }
        }
        if (test) {
            log.info("Test ok ");
            ordersEntity.setOrderStatut(EnumOrderStatut.Validate);
            ordersEntity.setOrderDate(getDate());

            contractsBean.validateContractsOrder();

            ordersServices.update(ordersEntity);

            initializationAfterValidation();
            contractsBean.initializationAfterValidation();
            setCptContracts(0);
            return "insurances";
        } else {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "fail");

            return "index";
        }
    }

    public void findOrderCanceledOrValidate() {
        log.info("OrdersBean : findOrderCanceledOrValidate");
        log.info("requestOrdersList : " + requestOrdersList);
        if ((requestOrdersList == "") || (!findOrdersCanceledOrValidateByIdOrder()) && (!findOrdersCanceledOrValidateByIdUser()) && (!findOrdersCanceledOrValidateByUsername())) {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "fxs.ordersList.requestError");
            ordersEntities = Collections.emptyList();
        } else {
            fail = "";
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

    protected boolean findOrdersCanceledOrValidateByIdOrder() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByIdOrder");
        try {
            int idOrder = Integer.parseInt(requestOrdersList);
            ordersEntities = ordersServices.findAllByIdOrderAndStatusIsValidateOrCanceled(idOrder);
            return ordersEntities.isEmpty() ? false : true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    protected boolean findOrdersCanceledOrValidateByIdUser() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByIdUser");
        try {
            int idUserTemp = Integer.parseInt(requestOrdersList);
            ordersEntities = ordersServices.findAllByIdUsersAndStatusIsValidateOrCanceled(idUserTemp);
            return ordersEntities.isEmpty() ? false : true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    protected boolean findOrdersCanceledOrValidateByUsername() {
        log.info("OrdersBean : findOrdersCanceledOrValidateByUsername");
        ordersEntities = ordersServices.findAllByUsernameUsersAndStatusIsValidateOrCanceled(requestOrdersList);
        return ordersEntities.isEmpty() ? false : true;
    }

    /**
     * Find orders By IdUsers and status is pending
     *
     * @return OrdersEntity or null
     */
    protected OrdersEntity findOrders_ByIdUsers_andStatusIsPending() {
        log.info("OrdersBean : findOrders_ByIdUsers_andStatusIsPending!");
        return ordersServices.findByIdUsersAndStatusIsPending(idUser);
    }


    /**
     * Create Order !
     */
    protected boolean createOrders() {
        log.info("OrdersBean : createOrders!");
        ordersEntity = new OrdersEntity();
        ordersEntity.setUsersByIdUsers(usersBean.findUserById(idUser));
        ordersEntity.setOrderDate(getDate());
        ordersEntity.setOrderStatut(EnumOrderStatut.Pending);
        return ordersServices.add(ordersEntity);
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
