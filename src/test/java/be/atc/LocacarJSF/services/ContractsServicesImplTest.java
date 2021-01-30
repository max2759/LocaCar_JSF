package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsServicesImplTest {
    public static Logger log = Logger.getLogger(ContractsServicesImplTest.class);


    private ContractsServices contractsServices;

    @BeforeEach
    public void initContracts() {
        log.info("Appel avant chaque test");
        contractsServices = new ContractsServicesImpl();
    }

    @AfterEach
    public void undefContracts() {
        log.info("Appel après chaque test");
        contractsServices = null;
    }

    @Test
    public void findContractByIdOrders_and_byIdCars_ShouldBeReturnFalse() {
        log.info("Recherche si contract existant dans une commande");

        // L'id order et l'id Cars ne doivent pas correspondre à un enregistrement en db
        int idOrder = 1;
        int idCars = 1;

        ContractsEntity contractsEntity = contractsServices.findContractByIdOrdersAndByIdCars(idOrder, idCars);

        Boolean test = contractsEntity == null ? false : true;

        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findContractByIdOrders_and_byIdCars_ShouldBeReturnTrue() {
        log.info("Recherche si contract existant dans une commande");

        // L'id order et l'id Cars doivent correspondre à un enregistrement en db
        int idOrder = 2;
        int idCars = 3;

        ContractsEntity contractsEntity = contractsServices.findContractByIdOrdersAndByIdCars(idOrder, idCars);

        Boolean test = contractsEntity == null ? false : true;

        assertThat(test).isEqualTo(true);
    }
}