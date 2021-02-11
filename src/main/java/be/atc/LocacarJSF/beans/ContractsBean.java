package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.ContractsServices;
import be.atc.LocacarJSF.services.ContractsServicesImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    // Remplacer par le prix final
    private double finalPrice;
    LocalDateTime dateEnd;

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
    private int timeLeasing;

    /**
     * Create contract
     *
     * @return True or false
     */
    protected boolean createContract() {
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

    protected boolean updateContract(ContractsEntity contractsEntity) {
        finalPrice = contractsEntity.getCarPrice() + (insurancesBean.getInsurancesEntity().getPrice() * this.getTimeLeasing());
        calculateDateEndContract();
        contractsEntity.setDateEnd(dateEnd);
        contractsEntity.setFinalPrice(finalPrice);
        return contractsServices.update(contractsEntity);
    }

    /**
     * find all contracts : if contract == leasing, find insurance contract !
     */
    public void findAllContracts(int idOrder) {
        contractsEntities = findAllContractsByIdOrder(idOrder);
        for (ContractsEntity c : contractsEntities) {
            if (c.getContractTypesByIdContractType().getLabel().equalsIgnoreCase("Leasing")) {
                ContractInsurancesEntity contractInsurancesEntity = contractInsurancesBean.findContractInsurancesByIdContract(c.getId());
                hmContractInsurances.put(c.getId(), contractInsurancesEntity);
            }
        }
    }

    /**
     * Calcul FinalPrice in Contract !!
     */
    public void calculFinalPriceContract() {
        this.finalPrice = adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Leasing ? (adsBean.getAdsEntity().getPrice() + (insurancesBean.getInsurancesEntity().getPrice() * timeLeasing)) : (adsBean.getAdsEntity().getPrice());
    }

    /**
     * Calcul date end for contract
     */
    protected void calculateDateEndContract() {
        dateEnd = LocalDateTime.now().plusMonths(timeLeasing);
    }

    /**
     * Find Contract by idOrders and by idCars
     *
     * @return True or false
     */
    protected ContractsEntity findContractByIdOrders_and_byIdCars() {
        return contractsServices.findContractByIdOrdersAndByIdCars(ordersBean.getOrdersEntity().getId(), adsBean.getAdsEntity().getCarsByIdCars().getId());
    }

    protected List<ContractsEntity> findAllContractsByIdOrder(int idOrder) {
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
}
