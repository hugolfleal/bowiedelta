package org.academiadecodigo.hackathon.persistence.model.order;

import org.academiadecodigo.hackathon.persistence.model.AbstractModel;
import org.academiadecodigo.hackathon.persistence.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
public class OrderItem extends AbstractModel {

    @ManyToOne (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne(cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
    private Product product;

    private int quantity;

    private int productId;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
