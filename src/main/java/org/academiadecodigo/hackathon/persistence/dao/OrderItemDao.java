package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao extends GenericDao<OrderItem>{


    public OrderItemDao() {
        super(OrderItem.class);
    }

}
