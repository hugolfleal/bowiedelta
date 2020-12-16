package org.academiadecodigo.hackathon.services;


import org.academiadecodigo.hackathon.persistence.dao.OrderDao;
import org.academiadecodigo.hackathon.persistence.dao.ProductDao;
import org.academiadecodigo.hackathon.persistence.model.order.Order;
import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;
    private OrderDao orderDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Product get(Integer id) {
        return productDao.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        return productDao.saveOrUpdate(product);
    }

    @Transactional
    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> list() {
        return productDao.findAll();
    }

    @Transactional
    public void addOrder(Integer id, Order order) throws IOException {

        Product product = productDao.findById(id);
        if (product == null) {
            throw new IOException();
        }

        product.addOrder(order);
        productDao.saveOrUpdate(product);
    }

    @Transactional
    public void removeOrder(Integer id, Integer orderId)
            throws IOException {

        Product product = productDao.findById(id);
        Order order = orderDao.findById(orderId);

        if (product == null) {
            throw new IOException();
        }

        if (order == null || !order.getUser().getId().equals(id)) {
            throw new IOException();
        }

        product.removeOrder(order);
        productDao.saveOrUpdate(product);
    }


}
