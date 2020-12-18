package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends GenericDao<Order>{


    public OrderDao() {
        super(Order.class);
    }

}
