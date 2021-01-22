package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class BrandsServicesImplTest {

    public static Logger log = Logger.getLogger(BrandsServicesImplTest.class);


    @Test
    public void addNewBrandsOK() {

        log.info("Le test démarre");
        BrandsEntity brandsEntity = new BrandsEntity();
        BrandsServices brandsServices = new BrandsServicesImpl();

        log.info("Création de l'objet");
        brandsEntity.setLabel("Opel");

        brandsServices.add(brandsEntity);

    }

    // tester si le not null marche
    @Test
    public void addNewBrandsNotOK() {

        log.info("Le test démarre");
        BrandsEntity brandsEntity = new BrandsEntity();
        BrandsServices brandsServices = new BrandsServicesImpl();

        log.info("Création de l'objet");

        brandsServices.add(brandsEntity);
    }
}