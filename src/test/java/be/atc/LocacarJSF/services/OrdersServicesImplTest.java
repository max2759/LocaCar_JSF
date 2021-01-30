package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrdersServicesImplTest {
    public static Logger log = Logger.getLogger(OrdersServicesImplTest.class);


    private OrdersServices ordersServices;

    @BeforeEach
    public void initOrders() {
        log.info("Appel avant chaque test");
        ordersServices = new OrdersServicesImpl();
    }

    @AfterEach
    public void undefOrders() {
        log.info("Appel après chaque test");
        ordersServices = null;
    }

    @Test
    public void findOrders_ByIdUsers_andStatusIsPending_ShouldBeReturnTrue() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Pending"));

        // Utiliser une entity : avec une order_status = Pending
        int idUser = 1;

        OrdersEntity ordersEntity = ordersServices.findByIdUsersAndStatusIsPending(idUser);

        Boolean test = ordersEntity == null ? false : true;

        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findOrders_ByIdUsers_andStatusIsPending_ShouldBeReturnFalse() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Pending"));

        // Utiliser une entity : qui n'a pas une order_status = Pending
        int idUser = 2;

        OrdersEntity ordersEntity = ordersServices.findByIdUsersAndStatusIsPending(idUser);

        Boolean test = ordersEntity == null ? false : true;

        assertThat(test).isEqualTo(false);
    }
}