package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "contractInsurancesBean")
@RequestScoped
public class ContractInsurancesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 2613097507996434366L;

    ContractInsurancesServices contractInsurancesServices = new ContractInsurancesServicesImpl();
    ContractInsurancesEntity contractInsurancesEntity;

    protected boolean createContractInsurances(int idContract, int idAssurance) {
        log.info("Create new Contract Assurance for Leasing !");

        contractInsurancesEntity = new ContractInsurancesEntity();

        contractInsurancesEntity.setIdContract(idContract);
        contractInsurancesEntity.setIdInsurance(idAssurance);

        return contractInsurancesServices.add(contractInsurancesEntity);
    }
}
