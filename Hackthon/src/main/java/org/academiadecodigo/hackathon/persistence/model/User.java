package org.academiadecodigo.hackathon.persistence.model;

import org.academiadecodigo.hackathon.persistence.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User extends AbstractModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer googleId;

    @OneToOne (cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(
            // propagate changes on customer entity to order entities
            cascade = {CascadeType.ALL},

            // make sure to remove orders if unlinked from customer
            orphanRemoval = true,

            // user customer foreign key on order table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "user",

            // fetch orders from database together with user
            fetch = FetchType.EAGER
    )
    private List<Order> orders = new ArrayList<>();
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Integer getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Integer googleId) {
        this.googleId = googleId;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {

        // printing recipients with lazy loading
        // and no session will cause issues
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", orders=" + orders +
                "} " + super.toString();
    }
}
