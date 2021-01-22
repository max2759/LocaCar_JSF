package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.InsurancesEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class InsurancesServicesImplTest {

    public static Logger log = Logger.getLogger(InsurancesServicesImplTest.class);

    @Test
    public void add() {
        log.info("Démarrage du test");
        InsurancesEntity insurancesEntity = new InsurancesEntity();
        InsurancesServices insurancesServices = new InsurancesServicesImpl();

        log.info("Création des objets");

        insurancesEntity.setActive(true);
        insurancesEntity.setDescription("Test2");
        insurancesEntity.setPrice(10);
        insurancesEntity.setLabel("test");

        log.info("Set Insurances");


        insurancesServices.add(insurancesEntity);

    }
}