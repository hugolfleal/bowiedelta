package org.academiadecodigo.hackathon.persistence.model.product;

import javax.persistence.Entity;

@Entity
public class MiscellaneousProduct extends Product{

    @Override
    public ProductType getProductType() {
        return ProductType.MISCELLANEOUS;
    }

}
