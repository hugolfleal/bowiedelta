package org.academiadecodigo.hackathon.persistence.model.product;

import org.academiadecodigo.hackathon.persistence.model.AbstractModel;
import org.academiadecodigo.hackathon.persistence.model.order.OrderItem;


import javax.persistence.*;
import java.util.LinkedList;
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
    //private ProductType productType;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.EAGER
    )
    private List<OrderItem> orderItems = new LinkedList<>();

    public abstract ProductType getProductType();

    /*public void setProductType(ProductType productType) {
        this.productType = productType;
    }*/

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        orderItems.remove(orderItem);
    }
}
