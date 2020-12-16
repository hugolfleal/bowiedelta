package org.academiadecodigo.hackathon.services;

import org.academiadecodigo.hackathon.persistence.dao.OrderDao;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private OrderDao orderDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order get(Integer id) {
        return orderDao.findById(id);
    }

    @Transactional
    public Order save(Order order) {
        return orderDao.saveOrUpdate(order);
    }

    @Transactional
    public void delete(Integer id) {
        orderDao.delete(id);
    }

    public List<Order> list() {
        return orderDao.findAll();
    }


}
