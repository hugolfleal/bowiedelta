package org.academiadecodigo.hackthon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class BusProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.BUS;
    }
}
