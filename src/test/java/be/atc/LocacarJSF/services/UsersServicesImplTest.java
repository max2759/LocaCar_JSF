package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsersServicesImplTest {
    public static Logger log = Logger.getLogger(UsersServicesImplTest.class);


    private UsersServices usersServices;

    @BeforeEach
    public void initUsers() {
        log.info("Appel avant chaque test");
        usersServices = new UsersServicesImpl();
    }

    @AfterEach
    public void undefUsers() {
        log.info("Appel après chaque test");
        usersServices = null;
    }

    @Test
    void findByIdUsers_shouldBeReturnEntity() {
        UsersEntity usersEntity = usersServices.findById(1);
        Boolean test;
        if (usersEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(true);
    }

    @ParameterizedTest(name = "{0} n'existe pas !")
    @ValueSource(ints = {0, 9999})
    public void findByIdUsers_shouldBeReturnNull(int arg) {
        UsersEntity usersEntity = usersServices.findById(arg);
        Boolean test;
        if (usersEntity == null) {
            test = false;
        } else {
            test = true;
        }
        assertThat(test).isEqualTo(false);
    }

    @Test
    void findAll() {
        List<UsersEntity> usersEntities = usersServices.findAll();
        Boolean test;

        if (!usersEntities.isEmpty()) {
            test = true;
        } else {
            test = false;
        }

        assertThat(test).isEqualTo(true);
    }

}