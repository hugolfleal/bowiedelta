package org.academiadecodigo.hackathon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class VirtualExperiencesProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.VIRTUAL_EXPERIENCES;
    }
}
