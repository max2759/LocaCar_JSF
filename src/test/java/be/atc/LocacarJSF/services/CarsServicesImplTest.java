package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsServicesImplTest {

    public static Logger log = Logger.getLogger(CarsServicesImplTest.class);

    private CarsServices carsServices;

    @BeforeEach
    public void initCars() {
        log.info("Appel avant chaque test");
        carsServices = new CarsServicesImpl();
    }

    @AfterEach
    public void undefCars() {
        log.info("Appel après chaque test");
        carsServices = null;
    }


    @Test
    void findByIdCar_shouldBeReturnEntity() {
        CarsEntity carsEntity = carsServices.findById(5);
        Boolean test;
        if (carsEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(true);
    }

    @ParameterizedTest(name = "{0} n'existe pas !")
    @ValueSource(ints = {0, 9999})
    public void findByIdCar_shouldBeReturnNull(int arg) {
        CarsEntity carsEntity = carsServices.findById(arg);
        Boolean test;
        if (carsEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        List<CarsEntity> carsEntities = carsServices.findAll();
        Boolean test;

        if (!carsEntities.isEmpty()) {
            test = true;
        } else {
            test = false;
        }

        assertThat(test).isEqualTo(true);
    }


}