package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CountriesEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class CountriesServicesImplTest {

    public static Logger log = Logger.getLogger(CountriesServicesImplTest.class);

    @Test
    void add() {
        log.info("debut du test");
        CountriesEntity countriesEntity = new CountriesEntity();
        countriesEntity.setLabel("Belgique");
        CountriesServices countriesServices = new CountriesServicesImpl();
        countriesServices.add(countriesEntity);
        log.info("fin du test");
    }
}