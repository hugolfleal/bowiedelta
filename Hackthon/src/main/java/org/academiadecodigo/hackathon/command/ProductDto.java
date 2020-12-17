package org.academiadecodigo.hackathon.command;

import org.academiadecodigo.hackathon.persistence.model.product.ProductType;

public class ProductDto {

    private double price;
    private String name;
    private String description;
    private ProductType productType;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
