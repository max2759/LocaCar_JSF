package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsOptionsServicesImplTest {

    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();
    public static Logger log = Logger.getLogger(CarsOptionsServicesImplTest.class);


    @Test
    public void findCarsOptionsByCarsId_shoudlReturnTrue() {
        int idCars = 18;

        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(idCars);
        Boolean test;

        if (!carsOptionsEntityList.isEmpty()) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findCarsOptionsByCarsId_shoudlReturnFalse() {
        int idCars = 200;

        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(idCars);
        Boolean test;

        if (!carsOptionsEntityList.isEmpty()) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void deleteCarOptionByID() {
        int idCars = 55;

        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(idCars);

        for (CarsOptionsEntity c : carsOptionsEntityList) {
            log.info("Début foreach" + c.getId());
            carsOptionsServices.deleteCarOptionByID(c.getId());
        }
    }


}