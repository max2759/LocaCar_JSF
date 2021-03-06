package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.ContractsServices;
import be.atc.LocacarJSF.services.ContractsServicesImpl;
import utils.JsfUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Younes - Arifi
 * Contract Bean
 */
@Named(value = "contractsBean")
@SessionScoped
public class ContractsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -2375526727476678004L;

    ContractsServices contractsServices = new ContractsServicesImpl();
    ContractsEntity contractsEntity;
    List<ContractsEntity> contractsEntities;
    List<ContractsEntity> contractsEntitiesFind;

    Map<Integer, ContractInsurancesEntity> hmContractInsurances = new HashMap<>();
    Map<Integer, ContractInsurancesEntity> hmContractInsurancesFind = new HashMap<>();
    Map<Integer, ContractsEntity> hmContractsLeasingDeadline = new HashMap<>();

    private double finalPrice;
    private LocalDateTime dateEnd;
    private int timeLeasing;
    private boolean showPopupEndLeasing = false;

    @Inject
    private ContractInsurancesBean contractInsurancesBean;
    @Inject
    private ContractTypesBean contractTypesBean;
    @Inject
    private OrdersBean ordersBean;
    @Inject
    private AdsBean adsBean;
    @Inject
    private InsurancesBean insurancesBean;
    @Inject
    private CarsBean carsBean;

    /**
     * Method initialization after Validation basket
     */
    protected void initializationAfterValidation() {
        contractsEntity = null;
        contractsEntities = Collections.emptyList();
        hmContractInsurances = new HashMap<>();
        hmContractsLeasingDeadline = new HashMap<>();
        finalPrice = 0;
        timeLeasing = 0;
    }

    /**
     * Close popup leasing Notification
     */
    public void hidePopupModal() {
        log.info("ContractsBean : hidePopupModal");
        showPopupEndLeasing = false;
    }

    /**
     * Create contract
     *
     * @return True or false
     */
    protected boolean createContract() {
        log.info("ContractsBean createContract!");
        contractsEntity = findContractByIdOrders_and_byIdCars();

        if (contractsEntity != null) {
            return false;
        }

        calculFinalPriceContract();
        calculateDateEndContract();

        contractsEntity = new ContractsEntity();
        contractsEntity.setOrdersByIdOrders(ordersBean.getOrdersEntity());
        contractsEntity.setCarsByIdCars(adsBean.getAdsEntity().getCarsByIdCars());
        contractsEntity.setDateStart(getDate());
        contractsEntity.setDateEnd(dateEnd);
        contractsEntity.setCarPrice(adsBean.getAdsEntity().getPrice());
        contractsEntity.setFinalPrice(finalPrice);
        contractsEntity.setChoiceEndLeasing(true);
        if (adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Sale) {
            contractsEntity.setContractTypesByIdContractType(contractTypesBean.findContractTypesById(1));
        } else {
            contractsEntity.setContractTypesByIdContractType(contractTypesBean.findContractTypesById(2));
        }

        boolean test = contractsServices.add(contractsEntity);
        if (test && adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Leasing) {
            test = contractInsurancesBean.createContractInsurances(insurancesBean.getInsurancesEntity());
        }
        return test;
    }

    /**
     * Create contract for End Leasing
     *
     * @param idContract type int
     * @return True or false
     */
    protected boolean createContractEndLeasing(int idContract) {
        log.info("ContractsBean createContractEndLeasing!");
        ContractsEntity contractsEntityTemp = contractsServices.findById(idContract);

        if (contractsEntityTemp == null) {
            return false;
        }

        CarsEntity carsEntity = carsBean.findCarsById(contractsEntityTemp.getCarsByIdCars().getId());
        carsEntity.setActive(true);
        carsBean.updateCar(carsEntity);

        double priceCar = contractsEntityTemp.getCarPrice() * 0.30;
        contractsEntity = new ContractsEntity();
        contractsEntity.setOrdersByIdOrders(ordersBean.getOrdersEntity());
        contractsEntity.setCarsByIdCars(contractsEntityTemp.getCarsByIdCars());
        contractsEntity.setDateStart(getDate());
        contractsEntity.setCarPrice(priceCar);
        contractsEntity.setFinalPrice(priceCar);
        contractsEntity.setChoiceEndLeasing(false);
        contractsEntity.setContractTypesByIdContractType(contractTypesBean.findContractTypesById(1));

        return contractsServices.add(contractsEntity);
    }

    /**
     * Method for update contract !
     *
     * @param contractsEntity ContractsEntity
     * @return boolean
     */
    protected boolean updateContract(ContractsEntity contractsEntity) {
        log.info("ContractsBean updateContract!");
        finalPrice = contractsEntity.getCarPrice() + (insurancesBean.getInsurancesEntity().getPrice() * this.getTimeLeasing());
        calculateDateEndContract();
        contractsEntity.setDateEnd(dateEnd);
        contractsEntity.setFinalPrice(finalPrice);
        return contractsServices.update(contractsEntity);
    }

    /**
     * Method for validate Contracts !
     */
    protected void validateContractsOrder() {
        for (ContractsEntity c : contractsEntities) {
            CarsEntity carsEntity = carsBean.setActiveCarFalse(c.getCarsByIdCars());
            carsBean.updateCar(carsEntity);

            c.setDateStart(getDate());
            c.setDateEnd(dateEnd);
            if (!c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                c.setChoiceEndLeasing(false);
            }
            ContractsEntity contractsEntityTmp = contractsServices.findContractByIdCarAndTypeIsLeasing(c.getCarsByIdCars().getId());
            if (contractsEntityTmp != null && contractsEntityTmp.getId() != c.getId()) {
                contractsEntityTmp.setChoiceEndLeasing(false);
                contractsServices.update(contractsEntityTmp);
            }
            contractsServices.update(c);
        }
    }

    /**
     * find all contracts. In basket
     */
    public void findAllContracts(int idOrder) {
        log.info("ContractsBean findAllContracts!");
        contractsEntities = findAllContractsByIdOrder(idOrder);
        if (!contractsEntities.isEmpty()) {
            foreachContractsEntitiesPutMap(false);
        }
    }

    /**
     * Find all contracts. In orders
     *
     * @param idOrder type int
     */
    protected void findAllContractsWhenFindOrders(int idOrder) {
        log.info("ContractsBean findAllContractsWhenFindOrders!");

        this.contractsEntitiesFind = findAllContractsByIdOrder(idOrder);
        if (!contractsEntitiesFind.isEmpty()) {
            foreachContractsEntitiesPutMap(true);
        }
    }

    /**
     * Method who put contracts insurances in map. if contract == leasing, find insurance contract !
     *
     * @param find If find == true then it's find order. If find == false, it's in basket.
     */
    protected void foreachContractsEntitiesPutMap(boolean find) {
        if (find) {
            for (ContractsEntity c : contractsEntitiesFind) {
                if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                    ContractInsurancesEntity contractInsurancesEntity = contractInsurancesBean.findContractInsurancesByIdContract(c.getId());
                    hmContractInsurancesFind.put(c.getId(), contractInsurancesEntity);
                }
            }
        } else {
            for (ContractsEntity c : contractsEntities) {
                if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                    ContractInsurancesEntity contractInsurancesEntity = contractInsurancesBean.findContractInsurancesByIdContract(c.getId());
                    hmContractInsurances.put(c.getId(), contractInsurancesEntity);
                }
            }
        }
    }

    /**
     * Calcul FinalPrice in Contract !!
     */
    public void calculFinalPriceContract() {
        log.info("ContractsBean calculFinalPriceContract!");
        this.finalPrice = adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Leasing ? (adsBean.getAdsEntity().getPrice() + (insurancesBean.getInsurancesEntity().getPrice() * timeLeasing)) : (adsBean.getAdsEntity().getPrice());
    }

    /**
     * Calcul date end for contract
     */
    protected void calculateDateEndContract() {
        log.info("ContractsBean calculateDateEndContract!");
        dateEnd = LocalDateTime.now().plusMonths(timeLeasing);
    }

    /**
     * Find Contract by idOrders and by idCars
     *
     * @return True or false
     */
    protected ContractsEntity findContractByIdOrders_and_byIdCars() {
        log.info("ContractsBean findContractByIdOrders_and_byIdCars!");
        return contractsServices.findContractByIdOrdersAndByIdCars(ordersBean.getOrdersEntity().getId(), adsBean.getAdsEntity().getCarsByIdCars().getId());
    }


    /**
     * Method to delete contract
     */
    public void deleteContract() {
        log.info("ContractsBean deleteContract!");
        FacesContext context = FacesContext.getCurrentInstance();

        contractsEntity = contractsServices.findById(Integer.parseInt(getParam("idContract")));

        if (contractsEntity == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "failDelete"), null));
            return;
        }

        boolean test = true;
        if (contractsEntity.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
            test = contractInsurancesBean.deleteContractInsurance(contractsEntity);
        }
        if (test) {
            test = contractsServices.delete(contractsEntity.getId());
        }

        if (test) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successDelete"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "failDelete"), null));
        }
        ordersBean.init();
    }

    /**
     * Clear Basket. Delete all contracts
     */
    public void clearBasket() {
        log.info("ContractsBean clearBasket!");

        FacesContext context = FacesContext.getCurrentInstance();

        log.info(contractsEntities);

        for (ContractsEntity c : contractsEntities
        ) {
            if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                contractInsurancesBean.deleteContractInsurance(c);
            }
            contractsServices.delete(c.getId());
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successDelete"), null));
        ordersBean.findOrderAndfindContracts();
    }

    /**
     * Find all contracts By Id Order And Deadline leasing is lower than 1 month
     * param List<OrdersEntity> List of OrdersEntity
     */
    protected void findAllContractsInAllMyOrdersForLeasingAndDeadlineIsLowerThan1Month(List<OrdersEntity> ordersEntities) {
        for (OrdersEntity o : ordersEntities) {
            List<ContractsEntity> contractsEntitiesDeadline = contractsServices.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(o.getId());
            if (!contractsEntitiesDeadline.isEmpty()) {
                showPopupEndLeasing = true;
                for (ContractsEntity c : contractsEntitiesDeadline) {
                    hmContractsLeasingDeadline.put(c.getId(), c);
                }
            }
        }
    }

    public void putChoiceEndLeasingFalse() {
        log.info("ContractsBean putChoiceEndLeasingFalse!");
        FacesContext context = FacesContext.getCurrentInstance();
        boolean test;
        contractsEntity = new ContractsEntity();
        int idContract = Integer.parseInt(getParam("idContract"));
        if (idContract != 0) {
            contractsEntity = contractsServices.findById(idContract);
        }
        if (contractsEntity != null) {
            contractsEntity.setChoiceEndLeasing(false);
            test = contractsServices.update(contractsEntity);
            hmContractsLeasingDeadline = new HashMap<>();
        } else {
            test = false;
        }

        if (test) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, JsfUtils.returnMessage(getLocale(), "successDelete"), null));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, JsfUtils.returnMessage(getLocale(), "failDelete"), null));
        }
        setShowPopupEndLeasing(false);
        ordersBean.findAllMyOrders();
    }

    /**
     * count Contract By id Order
     *
     * @param idOrder int
     * @return int
     */
    protected int countContractsByIdOrder(int idOrder) {
        return (int) contractsServices.countContractsByIdOrder(idOrder);
    }

    /**
     * Find All Contracts By Id Order
     *
     * @param idOrder type int
     * @return List of ContractsEntity
     */
    protected List<ContractsEntity> findAllContractsByIdOrder(int idOrder) {
        log.info("ContractsBean findAllContractsByIdOrder!");
        return contractsServices.findAllContractsByIdOrder(idOrder);
    }

    public ContractsEntity getContractsEntity() {
        return contractsEntity;
    }

    public void setContractsEntity(ContractsEntity contractsEntity) {
        this.contractsEntity = contractsEntity;
    }

    public List<ContractsEntity> getContractsEntities() {
        return contractsEntities;
    }

    public void setContractsEntities(List<ContractsEntity> contractsEntities) {
        this.contractsEntities = contractsEntities;
    }

    public Map<Integer, ContractInsurancesEntity> getHmContractInsurances() {
        return hmContractInsurances;
    }

    public void setHmContractInsurances(Map<Integer, ContractInsurancesEntity> hmContractInsurances) {
        this.hmContractInsurances = hmContractInsurances;
    }

    public Map<Integer, ContractInsurancesEntity> getHmContractInsurancesFind() {
        return hmContractInsurancesFind;
    }

    public void setHmContractInsurancesFind(Map<Integer, ContractInsurancesEntity> hmContractInsurancesFind) {
        this.hmContractInsurancesFind = hmContractInsurancesFind;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getTimeLeasing() {
        return timeLeasing;
    }

    public void setTimeLeasing(int timeLeasing) {
        this.timeLeasing = timeLeasing;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<ContractsEntity> getContractsEntitiesFind() {
        return contractsEntitiesFind;
    }

    public void setContractsEntitiesFind(List<ContractsEntity> contractsEntitiesFind) {
        this.contractsEntitiesFind = contractsEntitiesFind;
    }

    public Map<Integer, ContractsEntity> getHmContractsLeasingDeadline() {
        return hmContractsLeasingDeadline;
    }

    public void setHmContractsLeasingDeadline(Map<Integer, ContractsEntity> hmContractsLeasingDeadline) {
        this.hmContractsLeasingDeadline = hmContractsLeasingDeadline;
    }

    public boolean isShowPopupEndLeasing() {
        return showPopupEndLeasing;
    }

    public void setShowPopupEndLeasing(boolean showPopupEndLeasing) {
        this.showPopupEndLeasing = showPopupEndLeasing;
    }
}
