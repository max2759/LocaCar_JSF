package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.ContractsServices;
import be.atc.LocacarJSF.services.ContractsServicesImpl;
import utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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

    Map<Integer, ContractInsurancesEntity> hmContractInsurances = new HashMap<Integer, ContractInsurancesEntity>();

    private double finalPrice;
    private LocalDateTime dateEnd;
    private String success;
    private String fail;
    private boolean showBasket;
    private int timeLeasing;
    private int cptContracts;

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

    @PostConstruct
    public void init() {
        log.info("ContractsBean Post Construct!");
        initialisationFields();
    }

    /**
     * Initialisation fields
     */
    public void initialisationFields() {
        log.info("ContractsBean initialisationFields!");
        success = "";
        fail = "";
    }

    /**
     * Method initialization after Validation basket
     */
    protected void initializationAfterValidation() {
        contractsEntity = null;
        contractsEntities = Collections.emptyList();
        hmContractInsurances = new HashMap<Integer, ContractInsurancesEntity>();
        finalPrice = 0;
        showBasket = false;
        timeLeasing = 0;
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
     * Method for update contract !
     *
     * @param contractsEntity
     * @return
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


            log.info("contractEntity.getDateStart() : " + c.getDateStart());
            c.setDateStart(getDate());

            log.info("contractEntity.getDateStart( : )" + c.getDateStart());
            c.setDateEnd(getDate());
            contractsServices.update(c);
        }
    }

    /**
     * find all contracts : if contract == leasing, find insurance contract !
     */
    public void findAllContracts(int idOrder) {
        log.info("ContractsBean findAllContracts!");
        contractsEntities = findAllContractsByIdOrder(idOrder);
        if (!contractsEntities.isEmpty()) {
            showBasket = true;
            for (ContractsEntity c : contractsEntities) {
                if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                    ContractInsurancesEntity contractInsurancesEntity = contractInsurancesBean.findContractInsurancesByIdContract(c.getId());
                    hmContractInsurances.put(c.getId(), contractInsurancesEntity);
                }
            }
        } else {
            showBasket = false;
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
        contractsEntity = contractsServices.findById(Integer.parseInt(getParam("idContract")));

        if (contractsEntity == null) {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "failDelete");
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
            fail = "";
            success = JsfUtils.returnMessage(getLocale(), "successDelete");
        } else {
            success = "";
            fail = JsfUtils.returnMessage(getLocale(), "failDelete");
        }
        ordersBean.init();
    }

    /**
     * Clear Basket. Delete all contracts
     */
    public void clearBasket() {
        log.info("ContractsBean clearBasket!");
        log.info(contractsEntities);

        for (ContractsEntity c : contractsEntities
        ) {
            if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                contractInsurancesBean.deleteContractInsurance(c);
            }
            contractsServices.delete(c.getId());
        }
        success = JsfUtils.returnMessage(getLocale(), "successDelete");
        ordersBean.findOrderAndfindContracts();
    }

    protected void countContractsByIdOrder(int idOrder) {
        cptContracts = (int) contractsServices.countContractsByIdOrder(idOrder);
    }

    /**
     * Find All Contracts By Id Order
     *
     * @param idOrder
     * @return
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

    public boolean isShowBasket() {
        return showBasket;
    }

    public void setShowBasket(boolean showBasket) {
        this.showBasket = showBasket;
    }

    public int getCptContracts() {
        return cptContracts;
    }

    public void setCptContracts(int cptContracts) {
        this.cptContracts = cptContracts;
    }
}
