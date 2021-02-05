package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named(value = "ordersBean")
@SessionScoped
public class OrdersBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -5251107202124824837L;

    // Remplacer par dateEnd Leasing
    Date dateEnd = null;
    Map<Integer, ContractInsurancesEntity> hmContractInsurances = new HashMap<Integer, ContractInsurancesEntity>();
    // Remplacer par l'utilisateur
    private int idUser = 2;

    private OrdersEntity ordersEntity;
    private OrdersServices ordersServices = new OrdersServicesImpl();
    // Remplacer par le prix final
    private double finalPrice;
    private List<ContractsEntity> contractsEntities;

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

    /**
     * Method post construct
     */
    @PostConstruct
    public void init() {
        log.info("Post Construct");
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
        fieldsInitialization();
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
    }

    /**
     * Find order : if not null, find all contracts if contract == leasing, find insurance contract !
     */
    public void findOrderAndfindContracts() {

        ordersEntity = findOrders_ByIdUsers_andStatusIsPending();
        if (ordersEntity != null) {
            findAllContracts();
        }
    }

    /**
     * find all contracts : if contract == leasing, find insurance contract !
     */
    public void findAllContracts() {
        contractsEntities = contractsBean.findAllContractsByIdOrder(ordersEntity.getId());
        for (ContractsEntity c : contractsEntities)
            if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                ContractInsurancesEntity contractInsurancesEntity = contractInsurancesBean.findContractInsurancesByIdContract(c.getId());
                hmContractInsurances.put(c.getId(), contractInsurancesEntity);
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

    public Map<Integer, ContractInsurancesEntity> getHmContractInsurances() {
        return hmContractInsurances;
    }

    public void setHmContractInsurances(Map<Integer, ContractInsurancesEntity> hmContractInsurances) {
        this.hmContractInsurances = hmContractInsurances;
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

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<ContractsEntity> getContractsEntities() {
        return contractsEntities;
    }

    public void setContractsEntities(List<ContractsEntity> contractsEntities) {
        this.contractsEntities = contractsEntities;
    }
}
