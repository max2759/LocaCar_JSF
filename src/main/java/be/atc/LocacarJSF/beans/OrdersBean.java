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
import java.util.Date;
import java.util.List;

/**
 * @author Younes - Arifi
 * Orders Bean
 */
@Named(value = "ordersBean")
@SessionScoped
public class OrdersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -5251107202124824837L;

    // Remplacer par dateEnd Leasing
    Date dateEnd = null;
    // Remplacer par l'utilisateur
    private int idUser = 2;

    private OrdersEntity ordersEntity;
    private OrdersServices ordersServices = new OrdersServicesImpl();

    boolean showBasket;
    private String success;
    private String fail;
    @Inject
    private AdsBean adsBean;
    @Inject
    private ContractsBean contractsBean;
    @Inject
    private ContractInsurancesBean contractInsurancesBean;
    @Inject
    private UsersBean usersBean;
    private double priceOrder;

    /**
     * Method post construct
     */
    @PostConstruct
    public void init() {
        log.info("Post Construct");
        fieldsInitialization();
        findOrderAndfindContracts();
    }

    /**
     * Initialization fields
     */
    public void fieldsInitialization() {
        log.info("Field initialization !");
        success = "";
        fail = "";
    }

    /**
     * Add shop, call functions
     */
    public void addShop() {
        init();
        log.info("AdsEntity id : " + adsBean.getAdsEntity().getId());

        if (ordersEntity == null) {
            log.info("Aucun Orders n'est trouvé");
            createOrders();
        }

        if (contractsBean.createContract()) {
            success = JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopSuccess");
        } else {
            fail = JsfUtils.returnMessage(getLocale(), "fxs.addShopButton.addShopError");
        }
        findOrderAndfindContracts();
    }

    /**
     * Method calculate Price Order
     */
    public void calculatePriceOrder() {
        List<ContractsEntity> contractsEntities = contractsBean.findAllContractsByIdOrder(ordersEntity.getId());
        for (ContractsEntity c : contractsEntities
        ) {
            this.priceOrder += c.getFinalPrice();
        }
    }

    /**
     * Find order : if not null, find all contracts if contract == leasing, find insurance contract !
     */
    public void findOrderAndfindContracts() {

        ordersEntity = findOrders_ByIdUsers_andStatusIsPending();
        if (ordersEntity != null) {
            calculatePriceOrder();
            contractsBean.findAllContracts(ordersEntity.getId());
            showBasket = true;
        } else {
            showBasket = false;
        }
    }

    /**
     * Find orders By IdUsers and status is pending
     *
     * @return OrdersEntity or null
     */
    protected OrdersEntity findOrders_ByIdUsers_andStatusIsPending() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Pending"));
        return ordersServices.findByIdUsersAndStatusIsPending(idUser);
    }


    /**
     * Create Order !
     */
    protected boolean createOrders() {
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

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isShowBasket() {
        return showBasket;
    }

    public void setShowBasket(boolean showBasket) {
        this.showBasket = showBasket;
    }

    public double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(double priceOrder) {
        this.priceOrder = priceOrder;
    }
}
