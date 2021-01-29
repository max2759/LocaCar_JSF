package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.AdsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

}