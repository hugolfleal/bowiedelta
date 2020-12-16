package org.academiadecodigo.hackthon.persistence.model.product;

public class BusProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.BUS;
    }
}
