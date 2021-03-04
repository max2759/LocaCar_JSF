package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ContractsEntity;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void findContractById_ShouldReturnTrue() {
        log.info("Recherche d'un contract par ID");

        // Mettre l'id d'un contract existant
        int idContract = 50;

        ContractsEntity contractsEntity = contractsServices.findById(idContract);
        Boolean test = contractsEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findContractById_ShouldReturnFalse() {
        log.info("Recherche d'un contract par ID");

        // Mettre l'id d'un contract existant
        int idContract = 0;

        ContractsEntity contractsEntity = contractsServices.findById(idContract);
        Boolean test = contractsEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findContractByIdOrders_and_byIdCars_ShouldBeReturnFalse() {
        log.info("Recherche si contract existant dans une commande");

        // L'id order et l'id Cars ne doivent pas correspondre à un enregistrement en db
        int idOrder = 1;
        int idCars = 1;

        ContractsEntity contractsEntity = contractsServices.findContractByIdOrdersAndByIdCars(idOrder, idCars);

        Boolean test = contractsEntity != null;

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

        Boolean test = contractsEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findAllContract_byIdOrder_shouldBeReturnTrue() {
        // Mettre l'id Order valide d'un utilisateur
        int idOrder = 3;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = !contractsEntityList.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);

    }

    @Test
    public void findAllContract_byIdOrder_shouldBeReturnFalse() {
        // Mettre l'id Order non valide d'un utilisateur
        int idOrder = 99999;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = !contractsEntityList.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);

    }

    @Test
    public void findAllContract_byIdOrderEqual0_shouldBeReturnFalse() {
        // Mettre l'id Order non valide d'un utilisateur
        int idOrder = 0;

        List<ContractsEntity> contractsEntityList = contractsServices.findAllContractsByIdOrder(idOrder);

        boolean test = !contractsEntityList.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);


    }

    @Test
    void countContractsByIdOrder_shouldReturnTrue() {
        // mettre une idOrder valide
        int idOrder = 53;

        int i = (int) contractsServices.countContractsByIdOrder(idOrder);

        boolean test = i > 0;

        log.info("Le test vaut : " + test + " et il y a " + i + " contracts");
        assertThat(test).isEqualTo(true);

    }

    @Test
    void countContractsByIdOrder_shouldReturnFalse() {
        // mettre une idOrder invalide
        int idOrder = 0;

        int i = (int) contractsServices.countContractsByIdOrder(idOrder);

        boolean test = i > 0;

        log.info("Le test vaut : " + test + " et il y a " + i + " contracts");
        assertThat(test).isEqualTo(false);

    }

    @Test
    void findContractsByIdOrderWhereDeadlineIsLowerThan1Month() {
        // Mettre un idOrder valide, qui renvoie un leasing qui a une deadline inférieur à 1 mois
        int idOrder = 65;

        List<ContractsEntity> contractsEntities = contractsServices.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(idOrder);
        boolean test = !contractsEntities.isEmpty();

        assertThat(test).isEqualTo(true);

    }

    @Test
    void findContractsInAllOrdersForLeasingDeadline() {
        int idUser = 6;

        OrdersServices ordersServices = new OrdersServicesImpl();
        List<OrdersEntity> ordersEntities = ordersServices.findAllOrdersByIdUserAndStatusIsValidate(idUser);

        Map<Integer, ContractsEntity> hmContractsLeasingDeadline = new HashMap<>();

        for (OrdersEntity o : ordersEntities) {
            List<ContractsEntity> contractsEntities = contractsServices.findAllContractsByIdOrderAndDeadlineIsLowerThan1Month(o.getId());
            if (!contractsEntities.isEmpty()) {
                for (ContractsEntity c : contractsEntities) {
                    hmContractsLeasingDeadline.put(c.getId(), c);
                }
            }
        }
        boolean test = !hmContractsLeasingDeadline.isEmpty();

        assertThat(test).isEqualTo(true);
    }

    @Test
    void putChoiceEndLeasingFalse() {
        // Mettre un id de contrat valide qui un a : ChoiceEndLeasing = 1
        int idContract = 2;
        boolean test = false;
        ContractsEntity contractsEntity = contractsServices.findById(idContract);
        if (contractsEntity != null) {
            contractsEntity.setChoiceEndLeasing(false);
            test = contractsServices.update(contractsEntity);
        }
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findContractByIdCarAndTypeIsLeasing() {
        // Mettre un id de car valide qui un type leasing
        int idCar = 13;

        ContractsEntity contractsEntity = contractsServices.findContractByIdCarAndTypeIsLeasing(idCar);

        boolean test = contractsEntity != null;

        assertThat(test).isEqualTo(true);

    }
}