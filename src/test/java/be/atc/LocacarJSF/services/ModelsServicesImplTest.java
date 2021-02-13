package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.ModelsEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ModelsServicesImplTest {

    public static Logger log = Logger.getLogger(ModelsServicesImpl.class);
    ModelsServices modelsServices;

    @BeforeEach
    public void initInsurances() {
        log.info("Appel avant chaque test");
        modelsServices = new ModelsServicesImpl();
    }

    @AfterEach
    public void undefInsurances() {
        log.info("Appel après chaque test");
        modelsServices = null;
    }

    @Test
    void finModelsByBrandsId_shouldReturnTrue() {
        List<ModelsEntity> modelsEntityList = modelsServices.findModelsByBrandsId(1);

        boolean test = modelsEntityList.isEmpty() ? false : true;

        assertThat(test).isEqualTo(true);

    }

    @Test
    void finModelsByBrandsId_shouldReturnFalse() {
        List<ModelsEntity> modelsEntityList = modelsServices.findModelsByBrandsId(25);

        boolean test = modelsEntityList.isEmpty() ? false : true;

        assertThat(test).isEqualTo(false);

    }
}