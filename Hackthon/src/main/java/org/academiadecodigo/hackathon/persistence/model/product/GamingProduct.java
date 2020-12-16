package org.academiadecodigo.hackathon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class GamingProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.GAMING;
    }
}
