package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;
import be.atc.LocacarJSF.dao.entities.UsersEntity;
import be.atc.LocacarJSF.enums.EnumOrderStatut;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        Boolean test = ordersEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findOrders_ByIdUsers_andStatusIsPending_ShouldBeReturnFalse() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Pending"));

        // Utiliser une entity : qui n'a pas une order_status = Pending
        int idUser = 5;

        UsersServices usersServices = new UsersServicesImpl();
        UsersEntity usersEntity = usersServices.findById(idUser);

        OrdersEntity ordersEntity = ordersServices.findByIdUsersAndStatusIsPending(usersEntity.getId());

        Boolean test = ordersEntity != null;

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findOrders_ByIdUsers_andStatusIsValidateOrCanceled_ShouldBeReturnTrue() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity valide : avec une order_status = Validate ou Canceled
        int idUser = 5;

        List<OrdersEntity> ordersEntities = ordersServices.findAllByIdUsersAndStatusIsValidateOrCanceled(idUser);

        Boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findOrders_ByIdUsers_andStatusIsValidateOrCanceled_ShouldBeReturnFalse() {
        log.info(("Recherche d'Order, par Id Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity non valide : avec une order_status = Validate ou Canceled
        int idUser = 999999;

        List<OrdersEntity> ordersEntities = ordersServices.findAllByIdUsersAndStatusIsValidateOrCanceled(idUser);

        Boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findOrders_ByUsernameUsers_andStatusIsValidateOrCanceled_ShouldBeReturnTrue() {
        log.info(("Recherche d'Order, par username Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity valide : avec une order_status = Validate ou Canceled
        String username = "admin";

        List<OrdersEntity> ordersEntities = ordersServices.findAllByUsernameUsersAndStatusIsValidateOrCanceled(username);

        Boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findOrders_ByUsernameUsers_andStatusIsValidateOrCanceled_ShouldBeReturnFalse() {
        log.info(("Recherche d'Order, par username Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity valide : avec une order_status = Validate ou Canceled
        String username = "Blbalnenfie,c,ec,c,e";

        List<OrdersEntity> ordersEntities = ordersServices.findAllByUsernameUsersAndStatusIsValidateOrCanceled(username);

        Boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findOrders_ByIdOrder_andStatusIsValidateOrCanceled_ShouldBeReturnTrue() {
        log.info(("Recherche d'Order, par username Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity valide : avec une order_status = Validate ou Canceled
        int idOrder = 53;

        List<OrdersEntity> ordersEntities = ordersServices.findAllByIdOrderAndStatusIsValidateOrCanceled(idOrder);

        Boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }

    @Test
    public void findOrders_ByIdOrder_andStatusIsValidateOrCanceled_ShouldBeReturnFalse() {
        log.info(("Recherche d'Order, par username Users et Orders.Status == Validate or Canceled"));

        // Utiliser une entity valide : avec une order_status = Validate ou Canceled
        int idOrder = 0;

        List<OrdersEntity> ordersEntities = ordersServices.findAllByIdOrderAndStatusIsValidateOrCanceled(idOrder);


        boolean test = !ordersEntities.isEmpty();

        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(false);
    }

    @Test
    public void findAllOrdersByIdUser_and_StatusIsValidate() {
        int idUser = 6;

        boolean test = false;

        List<OrdersEntity> ordersEntities = ordersServices.findAllOrdersByIdUserAndStatusIsValidate(idUser);
        if (!ordersEntities.isEmpty()) {
            for (OrdersEntity o : ordersEntities) {
                if (o.getOrderStatut() == EnumOrderStatut.Validate) {
                    test = true;
                } else {
                    test = false;
                    break;
                }
            }
        }


        log.info("Le test vaut : " + test);
        assertThat(test).isEqualTo(true);
    }
}