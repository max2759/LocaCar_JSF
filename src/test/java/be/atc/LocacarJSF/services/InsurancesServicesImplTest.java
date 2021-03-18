package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InsurancesServicesImplTest {

    public static Logger log = Logger.getLogger(InsurancesServicesImplTest.class);


    private InsurancesServices insurancesServices;

    @BeforeEach
    public void initInsurances() {
        log.info("Appel avant chaque test");
        insurancesServices = new InsurancesServicesImpl();
    }

    @AfterEach
    public void undefInsurances() {
        log.info("Appel après chaque test");
        insurancesServices = null;
    }

    @Test
    public void addInsurances_shouldReturnPersistOk() {
        InsurancesEntity insurancesEntity = new InsurancesEntity();


        insurancesEntity.setActive(true);
        insurancesEntity.setDescription("Test3");
        insurancesEntity.setPrice(10);
        insurancesEntity.setLabel("test3");

        Boolean test = insurancesServices.add(insurancesEntity);

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void addInsurancesWithoutLabel_shouldReturnPersistError() {
        InsurancesEntity insurancesEntity = new InsurancesEntity();


        insurancesEntity.setActive(true);
        insurancesEntity.setDescription("Test2");
        insurancesEntity.setPrice(10);

        Boolean test = insurancesServices.add(insurancesEntity);

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void updateInsurances_shouldReturnMergeOk() {

        InsurancesEntity insurancesEntity = insurancesServices.findById(1);

        insurancesEntity.setActive(true);
        insurancesEntity.setDescription("New Test");
        insurancesEntity.setPrice(10);
        insurancesEntity.setLabel("New Test");

        Boolean test = insurancesServices.update(insurancesEntity);

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void updateInsurances_shouldReturnMergeError() {

        InsurancesEntity insurancesEntity = new InsurancesEntity();

        insurancesEntity.setActive(true);
        insurancesEntity.setDescription("New Test");
        insurancesEntity.setPrice(10);
        insurancesEntity.setLabel("New Test");

        Boolean test = insurancesServices.update(insurancesEntity);

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findByIdInsurance_shouldBeReturnEntity() {
        InsurancesEntity insurancesEntity = insurancesServices.findById(1);
        Boolean test;
        test = insurancesEntity != null;
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @ParameterizedTest(name = "{0} n'existe pas !")
    @ValueSource(ints = {0, 9999})
    public void findByIdInsurance_shouldBeReturnNull(int arg) {
        InsurancesEntity insurancesEntity = insurancesServices.findById(arg);
        Boolean test;
        test = insurancesEntity != null;
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        List<InsurancesEntity> insurancesEntities = insurancesServices.findAll();
        Boolean test;

        test = !insurancesEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findByLabelInsurance_shouldBeReturnEntity() {
        List<InsurancesEntity> insurancesEntities = insurancesServices.findByLabel("Full omnium");
        Boolean test;
        test = !insurancesEntities.isEmpty();
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findByLabelInsurance_shouldBeReturnFalse() {
        List<InsurancesEntity> insurancesEntities = insurancesServices.findByLabel("blabla");
        Boolean test;
        test = !insurancesEntities.isEmpty();
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findByLabelInsuranceEqualsNull_shouldBeReturnFalse() {
        List<InsurancesEntity> insurancesEntities = insurancesServices.findByLabel(null);
        Boolean test;
        test = !insurancesEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAllActiveInsurance() {
        List<InsurancesEntity> insurancesEntities = insurancesServices.findAllActiveInsurance();
        Boolean test = false;

        for (InsurancesEntity i : insurancesEntities) {
            if (i.isActive()) {
                test = true;
            } else {
                test = false;
                break;
            }
        }
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }
}