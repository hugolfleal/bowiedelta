package org.academiadecodigo.hackathon.persistence.model.order;

import org.academiadecodigo.hackathon.persistence.model.AbstractModel;
import org.academiadecodigo.hackathon.persistence.model.User;
import org.academiadecodigo.hackathon.persistence.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractModel {

    @ManyToOne (cascade = {CascadeType.ALL})
    private User user;

    @OneToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "order",

            // fetch accounts from database together with user
            fetch = FetchType.EAGER
    )
    private List<OrderItem> items = new LinkedList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setOrderItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addOrderItem(OrderItem orderItem){
        items.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        items.remove(orderItem);
    }


}
