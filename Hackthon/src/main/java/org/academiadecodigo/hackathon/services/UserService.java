package org.academiadecodigo.hackathon.services;

import org.academiadecodigo.hackathon.persistence.dao.OrderDao;
import org.academiadecodigo.hackathon.persistence.dao.UserDao;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;
    private OrderDao orderDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public User get(Integer id) {
        return userDao.findById(id);
    }

    public String getByGoogleId(String googleId) { return userDao.findByGoogleId(googleId);}

    public User getUserByGoogleId(String googleId) { return userDao.findUserByGoogleId(googleId);}

    @Transactional
    public User save(User user) {
        return userDao.saveOrUpdate(user);
    }

    @Transactional
    public void delete(Integer id) {
        userDao.delete(id);
    }

    public List<User> list() {
        return userDao.findAll();
    }

    @Transactional
    public void addOrder(Integer id, Order order) throws IOException {

        User user = userDao.findById(id);
        if (user == null) {
            throw new IOException();
        }

        user.addOrder(order);
        userDao.saveOrUpdate(user);
    }

    @Transactional
    public void cancelOrder(Integer id, Integer orderId)
            throws IOException {

        User user = userDao.findById(id);
        Order order = orderDao.findById(orderId);

        if (user == null) {
            throw new IOException();
        }

        if (order == null || !order.getUser().getId().equals(id)) {
            throw new IOException();
        }

        user.removeOrder(order);
        userDao.saveOrUpdate(user);
    }

}
