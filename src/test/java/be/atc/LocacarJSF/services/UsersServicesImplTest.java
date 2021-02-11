package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    void add() {
        LocalDateTime currentDate = LocalDateTime.now();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        log.info("debut du test");
        UsersEntity usersEntity = new UsersEntity();
        UsersServices usersServices = new UsersServicesImpl();

        usersEntity.setFirstname("Belgique");
        usersEntity.setLastname("toto");
        usersEntity.setActive(true);
        usersEntity.setPassword("pass");
        usersEntity.setRegisterDate(currentDate);
        usersEntity.setBirthdate(currentDate);


        Boolean test = usersServices.add(usersEntity);

        log.info(test);
        assertThat(test).isEqualTo(false);
        log.info("fin du test");
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

    @Test
    void findByUsernameAndPassword() {
        UsersEntity usersEntity = usersServices.findByUsernameAndPassword("lolo", "lolo");
        boolean test;
        if (usersEntity != null) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(true);
    }
}