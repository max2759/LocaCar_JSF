package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.RolesEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RolesServicesImplTest {

    public static Logger log = Logger.getLogger(RolesServicesImplTest.class);
    private RolesServices rolesServices;

    @BeforeEach
    public void initRoles() {
        log.info("Appel avant chaque test");
        rolesServices = new RolesServicesImpl();
    }


    @Test
    void findAll() {

        List<RolesEntity> rolesEntities = rolesServices.findAll();
        Boolean test;

        if (!rolesEntities.isEmpty()) {
            test = true;
        } else {
            test = false;
        }

        assertThat(test).isEqualTo(true);
    }

}