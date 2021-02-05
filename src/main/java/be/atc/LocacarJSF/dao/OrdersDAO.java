package be.atc.LocacarJSF.dao;

import be.atc.LocacarJSF.dao.entities.OrdersEntity;

import java.util.List;

/**
 * @author Younes - Arifi
 */
public interface OrdersDAO {

    public boolean add(OrdersEntity ordersEntity);

    public boolean update(OrdersEntity ordersEntity);

    public List<OrdersEntity> findAll();

    public OrdersEntity findById(int id);

    OrdersEntity findByIdUsersAndStatusIsPending(int idUser);
}
