package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.CarsColorsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsColorsServicesImplTest {

    public static Logger log = Logger.getLogger(CarsColorsServicesImpl.class);

    CarsColorsServices carsColorsServices = new CarsColorsServicesImpl();

    @Test
    void findByLabelList_shouldReturnTrue() {


        List<CarsColorsEntity> carsColorsEntityList = carsColorsServices.findByLabelList("Noir");
        boolean test = carsColorsEntityList.isEmpty() ? false : true;
        assertThat(test).isEqualTo(true);
    }

    @Test
    void findByLabelList_shouldReturnFalse() {

        CarsColorsEntity carsColorsEntity = carsColorsServices.findByLabel("bleu");
        boolean test = carsColorsEntity == null ? false : true;
        assertThat(test).isEqualTo(false);
    }
}