package be.atc.LocacarJSF.services;

import be.atc.LocacarJSF.dao.OrdersDAO;
import be.atc.LocacarJSF.dao.OrdersDAOImpl;
import be.atc.LocacarJSF.dao.entities.OrdersEntity;

import java.util.List;

public class OrdersServicesImpl implements OrdersServices {

    OrdersDAO ordersDAO = new OrdersDAOImpl();

    /**
     * Add entity
     *
     * @param ordersEntity
     * @return
     */
    @Override
    public boolean add(OrdersEntity ordersEntity) {
        if (ordersEntity != null) {
            return ordersDAO.add(ordersEntity);
        }
        return false;
    }

    /**
     * Update entity
     *
     * @param ordersEntity
     * @return
     */
    @Override
    public boolean update(OrdersEntity ordersEntity) {
        if (ordersEntity != null && findById(ordersEntity.getId()) != null) {
            ordersDAO.update(ordersEntity);
            return true;
        }
        return false;
    }

    /**
     * Find all entities
     *
     * @return
     */
    @Override
    public List<OrdersEntity> findAll() {
        return ordersDAO.findAll();
    }

    /**
     * Find entity by id
     *
     * @param id
     * @return
     */
    @Override
    public OrdersEntity findById(int id) {
        if (id != 0) {
            return ordersDAO.findById(id);
        }
        return null;
    }

    @Override
    public OrdersEntity findByIdUsersAndStatusIsPending(int idUser) {
        if (idUser != 0) {
            return ordersDAO.findByIdUsersAndStatusIsPending(idUser);
        }
        return null;
    }
}
