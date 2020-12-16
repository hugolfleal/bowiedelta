package org.academiadecodigo.hackthon.persistence.model.product;

public class TripProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.TRIP;
    }
}
