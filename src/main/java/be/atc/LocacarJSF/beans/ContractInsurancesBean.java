package be.atc.LocacarJSF.beans;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import be.atc.LocacarJSF.services.ContractInsurancesServices;
import be.atc.LocacarJSF.services.ContractInsurancesServicesImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "contractInsurancesBean")
@SessionScoped
public class ContractInsurancesBean extends ExtendBean implements Serializable {

    private static final long serialVersionUID = 2613097507996434366L;

    ContractInsurancesServices contractInsurancesServices = new ContractInsurancesServicesImpl();
    ContractInsurancesEntity contractInsurancesEntity;

    @Inject
    private ContractsBean contractsBean;

    protected boolean createContractInsurances(InsurancesEntity insurancesEntity) {
        log.info("Create new Contract Assurance for Leasing !");

        contractInsurancesEntity = new ContractInsurancesEntity();

        contractInsurancesEntity.setContractsByIdContract(contractsBean.getContractsEntity());
        contractInsurancesEntity.setInsurancesByIdInsurance(insurancesEntity);

        return contractInsurancesServices.add(contractInsurancesEntity);
    }

    protected ContractInsurancesEntity findContractInsurancesByIdContract(int idContract) {
        return contractInsurancesServices.findByIdContract(idContract);
    }

    public ContractInsurancesEntity getContractInsurancesEntity() {
        return contractInsurancesEntity;
    }

    public void setContractInsurancesEntity(ContractInsurancesEntity contractInsurancesEntity) {
        this.contractInsurancesEntity = contractInsurancesEntity;
    }
}
