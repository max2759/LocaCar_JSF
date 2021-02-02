package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.UsersEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersServiceImplTest {

    public static Logger log = Logger.getLogger(UsersServiceImplTest.class);

    @Test
    void add() {
        Date currentDate = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");

        log.info("debut du test");
        UsersEntity usersEntity = new UsersEntity();
        UsersServices usersServices = new UsersServicesImpl();

        usersEntity.setFirstname("Belgique");
        usersEntity.setLastname("toto");
        usersEntity.setIdRoles(1);
        usersEntity.setActive(true);
        usersEntity.setPassword("pass");
        usersEntity.setRegisterDate(currentDate);
        usersEntity.setBirthdate(currentDate);


        Boolean test = usersServices.add(usersEntity);

        log.info(test);
        assertThat(test).isEqualTo(false);
        log.info("fin du test");
    }


}
