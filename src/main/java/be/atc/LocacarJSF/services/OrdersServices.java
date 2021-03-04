package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface OrdersServices {
    boolean add(OrdersEntity ordersEntity);

    boolean update(OrdersEntity ordersEntity);

    List<OrdersEntity> findAll();

    OrdersEntity findById(int id);

    OrdersEntity findByIdUsersAndStatusIsPending(int idUser);

    List<OrdersEntity> findAllByIdUsersAndStatusIsValidateOrCanceled(int idUser);

    List<OrdersEntity> findAllByUsernameUsersAndStatusIsValidateOrCanceled(String username);

    List<OrdersEntity> findAllByIdOrderAndStatusIsValidateOrCanceled(int idOrder);

    List<OrdersEntity> findAllOrdersByIdUserAndStatusIsValidate(int idUser);
}
