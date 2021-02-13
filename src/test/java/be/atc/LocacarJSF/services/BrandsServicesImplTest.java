package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.BrandsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrandsServicesImplTest {

    public static Logger log = Logger.getLogger(BrandsServicesImplTest.class);

    BrandsServices brandsServices = new BrandsServicesImpl();

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

    @Test
    public void findById() {

        BrandsEntity brandsEntity = brandsServices.findById(1);
        Boolean test;
        if (brandsServices == null) {
            test = false;
        } else {
            test = true;
        }
        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findByLabel_shouldReturnTrue() {

        BrandsEntity brandsEntityList = brandsServices.findByLabel("Opel");
        Boolean test = brandsEntityList == null ? false : true;
        assertThat(test).isEqualTo(true);
    }


    @Test
    public void findByLabel_shouldReturnFalse() {

        BrandsEntity brandsEntityList = brandsServices.findByLabel("caca");
        Boolean test = brandsEntityList == null ? false : true;
        assertThat(test).isEqualTo(false);
    }
}