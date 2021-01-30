package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;

import java.util.List;

public interface OrdersServices {
    boolean add(OrdersEntity ordersEntity);

    boolean update(OrdersEntity ordersEntity);

    List<OrdersEntity> findAll();

    OrdersEntity findById(int id);

}
