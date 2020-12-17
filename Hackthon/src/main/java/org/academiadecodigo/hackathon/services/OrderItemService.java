package org.academiadecodigo.hackathon.services;


import org.academiadecodigo.hackathon.persistence.dao.OrderItemDao;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {

    private OrderItemDao orderItemDao;

    @Autowired
    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public OrderItem get(Integer id) {
        return orderItemDao.findById(id);
    }

    @Transactional
    public OrderItem save(OrderItem orderItem) {
        return orderItemDao.saveOrUpdate(orderItem);
    }

    @Transactional
    public void delete(Integer id) {
        orderItemDao.delete(id);
    }

    public List<OrderItem> list() {
        return orderItemDao.findAll();
    }


}
