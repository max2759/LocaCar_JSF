package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.enums.EnumTypeAds;
import be.atc.LocacarJSF.services.ContractsServices;
import be.atc.LocacarJSF.services.ContractsServicesImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "contractsBean")
@SessionScoped
public class ContractsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -2375526727476678004L;

    ContractsServices contractsServices = new ContractsServicesImpl();
    ContractsEntity contractsEntity;
    List<ContractsEntity> contractsEntities;


    // Remplacer par le prix final
    private double finalPrice;

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

        contractsEntity = new ContractsEntity();
        contractsEntity.setOrdersByIdOrders(ordersBean.getOrdersEntity());
        contractsEntity.setCarsByIdCars(adsBean.getAdsEntity().getCarsByIdCars());
        contractsEntity.setDateStart(getDate());
        contractsEntity.setDateEnd(ordersBean.getDateEnd());
        contractsEntity.setFinalPrice(ordersBean.getFinalPrice());
        contractsEntity.setChoiceEndLeasing(true);
        if (adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Sale) {
            contractsEntity.setContractTypesByIdContractType(contractTypesBean.findContractTypesById(1));
        } else {
            contractsEntity.setContractTypesByIdContractType(contractTypesBean.findContractTypesById(2));
        }

        Boolean test = contractsServices.add(contractsEntity);

        if (test && adsBean.getAdsEntity().getTypeAds() == EnumTypeAds.Leasing) {
            test = contractInsurancesBean.createContractInsurances(insurancesBean.getInsurancesEntity());
        }
        return test;
    }

    /**
     * Find Contract by idOrders and by idCars
     *
     * @return True or false
     */
    protected ContractsEntity findContractByIdOrders_and_byIdCars() {
        log.info("id order : " + ordersBean.getOrdersEntity().getId());
        log.info("id Car : " + adsBean.getAdsEntity().getCarsByIdCars().getId());
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
}
