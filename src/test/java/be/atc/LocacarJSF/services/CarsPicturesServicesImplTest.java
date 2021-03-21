package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsPicturesServicesImplTest {

    CarsPicturesServices carsPicturesServices = new CarsPicturesServicesImpl();


    @Test
    void findByLabel_shouldReturnTrue() {
        String label = "mc1.jpg";
        CarsPicturesEntity carsPicturesEntity = carsPicturesServices.findByLabel(label);
        Boolean test = carsPicturesEntity == null ? false : true;
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findByLabel_shouldReturnFalse() {
        String label = "tst.jpg";
        CarsPicturesEntity carsPicturesEntity = carsPicturesServices.findByLabel(label);
        Boolean test = carsPicturesEntity == null ? false : true;
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findCarsPictureByIdCars_shouldReturnTrue() {
        int idCars = 34;
        List<CarsPicturesEntity> carsPicturesEntityList = carsPicturesServices.findCarsPicturesByIdCars(idCars);
        Boolean test;

        if (!carsPicturesEntityList.isEmpty()) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(true);

    }

    @Test
    void findCarsPictureByIdCars_shouldReturnFalse() {
        int idCars = 48;
        List<CarsPicturesEntity> carsPicturesEntityList = carsPicturesServices.findCarsPicturesByIdCars(idCars);
        Boolean test;

        if (!carsPicturesEntityList.isEmpty()) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findOnePicturesByIdCars_shouldReturnTrue() {
        int idCars = 52;
        CarsPicturesEntity carsPicturesEntity = carsPicturesServices.findOnePicturesByIdCars(idCars);
        Boolean test = carsPicturesEntity == null ? false : true;
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findOnePicturesByIdCars_shouldReturnFalse() {
        int idCars = 200;
        CarsPicturesEntity carsPicturesEntity = carsPicturesServices.findOnePicturesByIdCars(idCars);
        Boolean test = carsPicturesEntity == null ? false : true;
        assertThat(test).isEqualTo(false);
    }

}