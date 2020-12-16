package org.academiadecodigo.hackthon.persistence.model.product;

public class GamingProduct extends Product{
    @Override
    public ProductType getProductType() {
        return ProductType.GAMING;
    }
}
