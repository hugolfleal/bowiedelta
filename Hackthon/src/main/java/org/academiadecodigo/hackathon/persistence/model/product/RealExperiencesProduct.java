package org.academiadecodigo.hackathon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class RealExperiencesProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.REAL_EXPERIENCES;
    }
}
