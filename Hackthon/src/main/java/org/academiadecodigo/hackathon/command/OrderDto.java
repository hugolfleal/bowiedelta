package org.academiadecodigo.hackathon.command;

import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class OrderDto {

    private Integer id;

    @NotNull(message = "Quantity is mandatory")
    @NotBlank(message = "Quantity is mandatory")
    private int quantity;

    @DateTimeFormat
    private Date date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
