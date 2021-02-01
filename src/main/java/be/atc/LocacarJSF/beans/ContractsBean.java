package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.services.ContractsServices;
import be.atc.LocacarJSF.services.ContractsServicesImpl;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named(value = "contractsBean")
@ViewScoped
public class ContractsBean extends ExtendBean implements Serializable {
    private static final long serialVersionUID = -2375526727476678004L;

    ContractsServices contractsServices = new ContractsServicesImpl();
    ContractsEntity contractsEntity;

    @Inject
    private ContractInsurancesBean contractInsurancesBean;

    /**
     * Create contract
     *
     * @param idOrder
     * @param adsEntity
     * @param finalPrice
     * @param dateEnd
     * @param typeAds
     * @param idAssurance
     * @return True or false
     */
    protected boolean createContract(int idOrder, AdsEntity adsEntity, double finalPrice, Date dateEnd, String typeAds, int idAssurance) {
        contractsEntity = findContractByIdOrders_and_byIdCars(idOrder, adsEntity.getIdCars());

        if (contractsEntity != null) {
            return false;
        }

        contractsEntity = new ContractsEntity();
        contractsEntity.setIdOrders(idOrder);
        contractsEntity.setIdCars(adsEntity.getIdCars());
        contractsEntity.setDateStart(getDate());
        contractsEntity.setDateEnd(dateEnd);
        contractsEntity.setFinalPrice(finalPrice);
        contractsEntity.setChoiceEndLeasing(true);
        if (typeAds.equalsIgnoreCase("Sale")) {
            contractsEntity.setIdContractType(1);
        } else {
            contractsEntity.setIdContractType(2);
            contractInsurancesBean.createContractInsurances(contractsEntity.getId(), idAssurance);
        }

        return contractsServices.add(contractsEntity);
    }

    /**
     * Find Contract by idOrders and by idCars
     *
     * @param idOrder idOrder
     * @param idCars  idCar
     * @return True or false
     */
    protected ContractsEntity findContractByIdOrders_and_byIdCars(int idOrder, int idCars) {
        return contractsServices.findContractByIdOrdersAndByIdCars(idOrder, idCars);
    }
}
