package org.academiadecodigo.hackathon.persistence.model.product;

import org.academiadecodigo.hackathon.persistence.model.AbstractModel;
import org.academiadecodigo.hackathon.persistence.model.order.Order;


import javax.persistence.*;
import java.util.List;

/**
 * A generic account model entity to be used as a base for concrete types of accounts
 */
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class Product extends AbstractModel {
    private double price;
    private String name;
    private String description;

    @ManyToMany(
            // fetch all security groups when fetching a user
            fetch = FetchType.EAGER
    )
    private List<Order> orders;

    public abstract ProductType getProductType();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }
}
