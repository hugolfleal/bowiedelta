package org.academiadecodigo.hackathon.persistence.model.order;

import org.academiadecodigo.hackathon.persistence.model.AbstractModel;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.persistence.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class Order extends AbstractModel {
    private int quantity;
    private Date date;

    @ManyToOne
    private User user;

    @ManyToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "orders",

            // fetch accounts from database together with user
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProducts(Product product){
        products.add(product);
    }

    public void removeProducts(Product product){
        products.remove(product);
    }
}
