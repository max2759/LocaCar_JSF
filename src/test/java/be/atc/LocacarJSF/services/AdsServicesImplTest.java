package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdsServicesImplTest {
    public static Logger log = Logger.getLogger(AdsServicesImplTest.class);


    private AdsServices adsServices;

    @BeforeEach
    public void initAds() {
        log.info("Appel avant chaque test");
        adsServices = new AdsServicesImpl();
    }

    @AfterEach
    public void undefAds() {
        log.info("Appel après chaque test");
        adsServices = null;
    }

    @Test
    void findByIdAds_shouldBeReturnEntity() {

        // Mettre l'Id d'une entité existante
        AdsEntity adsEntity = adsServices.findById(1);
        Boolean test;
        if (adsEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(true);
    }

    @ParameterizedTest(name = "{0} n'existe pas !")
    @ValueSource(ints = {0, 9999})
    public void findByIdAds_shouldBeReturnNull(int arg) {
        AdsEntity adsEntity = adsServices.findById(arg);
        Boolean test;
        if (adsEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        List<AdsEntity> adsEntities = adsServices.findAll();
        Boolean test;

        if (!adsEntities.isEmpty()) {
            test = true;
        } else {
            test = false;
        }

        assertThat(test).isEqualTo(true);
    }


    @Test
    void checkIfAdsDateEndIsGreaterThanToday_shouldBeReturnTrue() {
        Date date = new Date();

        // Mettre l'Id d'une entité qui a une date de fin supérieur à aujourd'hui
        AdsEntity adsEntity = adsServices.findById(1);
        Boolean test = adsEntity.getDateEnd().after(date) == true ? true : false;

        assertThat(test).isEqualTo(true);
    }

    @Test
    void checkIfAdsDateEndIsLessThanToday_shouldBeReturnFalse() {
        Date date = new Date();

        // Mettre l'Id d'une entité qui a une date de fin supérieur à aujourd'hui
        AdsEntity adsEntity = adsServices.findById(1);
        Boolean test = adsEntity.getDateEnd().before(date) == true ? true : false;


        assertThat(test).isEqualTo(false);
    }

    @Test
    void checkIfAdsIsActive_shouldBeReturnTrue() {
        // Mettre l'Id d'une entité : isActive = true
        AdsEntity adsEntity = adsServices.findById(1);
        Boolean test = adsEntity.isActive() == true ? true : false;


        assertThat(test).isEqualTo(true);
    }

    @Test
    void checkIfAdsIsActive_ShouldBeReturnFalse() {

        // Mettre l'Id d'une entité : isActive = false
        AdsEntity adsEntity = adsServices.findById(2);
        Boolean test = adsEntity.isActive() == true ? true : false;


        assertThat(test).isEqualTo(false);
    }

    @Test
    void checkIfCarsByIdCarsIsActive_shouldBeReturnTrue() {
        // Mettre l'Id d'une entité : isActive = true
        AdsEntity adsEntity = adsServices.findById(1);
        Boolean test = adsEntity.getCarsByIdCars().isActive() == true ? true : false;


        assertThat(test).isEqualTo(true);
    }

    @Test
    void checkIfCarsByIdCarsIsActive_shouldBeReturnFalse() {

        // Mettre l'Id d'une entité : isActive = false
        AdsEntity adsEntity = adsServices.findById(2);
        Boolean test = adsEntity.getCarsByIdCars().isActive() == true ? true : false;


        assertThat(test).isEqualTo(false);
    }
}