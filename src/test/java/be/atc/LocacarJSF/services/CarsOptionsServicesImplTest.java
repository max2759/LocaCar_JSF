package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsOptionsEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsOptionsServicesImplTest {

    private CarsOptionsServices carsOptionsServices = new CarsOptionsServicesImpl();


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
        int idCars = 49;

        List<CarsOptionsEntity> carsOptionsEntityList = carsOptionsServices.findCarsOptionsByCarsId(idCars);
        Boolean test;

        if (!carsOptionsEntityList.isEmpty()) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(false);
    }
}