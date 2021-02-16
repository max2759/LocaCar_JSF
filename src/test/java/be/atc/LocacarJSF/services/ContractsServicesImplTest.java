package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findContractByIdOrders_and_byIdCars_ShouldBeReturnTrue() {
        log.info("Recherche si contract existant dans une commande");

        // L'id order et l'id Cars doivent correspondre à un enregistrement en db
        int idOrder = 38;
        int idCars = 3;

        ContractsEntity contractsEntity = contractsServices.findContractByIdOrdersAndByIdCars(idOrder, idCars);

        Boolean test = contractsEntity == null ? false : true;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findAllContract_byIdOrder_shouldBeReturnTrue() {
        // Mettre l'id Order valide d'un utilisateur
        int idOrder = 3;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = contractsEntityList.isEmpty() ? false : true;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    public void findAllContract_byIdOrder_shouldBeReturnFalse() {
        // Mettre l'id Order non valide d'un utilisateur
        int idOrder = 99999;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = contractsEntityList.isEmpty() ? false : true;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }

    @Test
    public void findAllContract_byIdOrderEqual0_shouldBeReturnFalse() {
        // Mettre l'id Order non valide d'un utilisateur
        int idOrder = 0;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = contractsEntityList.isEmpty() ? false : true;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);


    }

    @Test
    void countContractsByIdOrder_shouldReturnTrue() {
        // mettre une idOrder valide
        int idOrder = 53;

        int i = (int) contractsServices.countContractsByIdOrder(idOrder);

        boolean test = i > 0 ? true : false;

        log.info("Le test vaut : " + test + " et il y a " + i + " contracts");
        assertThat(test).isEqualTo(true);

    }

    @Test
    void countContractsByIdOrder_shouldReturnFalse() {
        // mettre une idOrder invalide
        int idOrder = 0;

        int i = (int) contractsServices.countContractsByIdOrder(idOrder);

        boolean test = i > 0 ? true : false;

        log.info("Le test vaut : " + test + " et il y a " + i + " contracts");
        assertThat(test).isEqualTo(false);

    }
}