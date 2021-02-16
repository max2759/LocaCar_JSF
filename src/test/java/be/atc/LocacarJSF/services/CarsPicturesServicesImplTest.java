package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsPicturesEntity;
import org.junit.jupiter.api.Test;

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
}