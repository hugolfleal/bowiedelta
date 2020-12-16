package org.academiadecodigo.hackathon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class TripProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.TRIP;
    }
}
