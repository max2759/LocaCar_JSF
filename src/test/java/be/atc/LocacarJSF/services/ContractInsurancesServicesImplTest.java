package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractInsurancesEntity;
import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContractInsurancesServicesImplTest {
    public static Logger log = Logger.getLogger(ContractInsurancesServicesImplTest.class);


    private ContractInsurancesServices contractInsurancesServices;

    @BeforeEach
    public void initContracts() {
        log.info("Appel avant chaque test");
        contractInsurancesServices = new ContractInsurancesServicesImpl();
    }

    @AfterEach
    public void undefContracts() {
        log.info("Appel après chaque test");
        contractInsurancesServices = null;
    }

    @Test
    public void findContractInsurancesByIdContract_shouldBeReturnTrue() {

        // mettre un idContract correct et qui correspond à un leasing
        int idContract = 10;

        ContractsServices contractsServices = new ContractsServicesImpl();
        ContractsEntity contractsEntity = contractsServices.findById(idContract);
        ContractInsurancesEntity contractInsurancesEntity = null;
        if ((contractsEntity != null) && (contractsEntity.getContractTypesByIdContractType().getId() == 2)) {
            contractInsurancesEntity = contractInsurancesServices.findByIdContract(idContract);
        }

        Boolean test = contractInsurancesEntity != null ? true : false;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findContractInsurancesByIdContract_shouldBeReturnFalse() {

        // mettre un idContract correct et qui correspond à une vente (ou mettre un idContract incorrecte)
        int idContract = 99;

        ContractsServices contractsServices = new ContractsServicesImpl();
        ContractsEntity contractsEntity = contractsServices.findById(idContract);
        ContractInsurancesEntity contractInsurancesEntity = null;
        if ((contractsEntity != null) && (contractsEntity.getContractTypesByIdContractType().getId() == 2)) {
            contractInsurancesEntity = contractInsurancesServices.findByIdContract(idContract);
        }

        Boolean test = contractInsurancesEntity != null ? true : false;
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }
}