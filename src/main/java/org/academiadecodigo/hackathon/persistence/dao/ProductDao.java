package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends GenericDao<Product>{


    public ProductDao() {
        super(Product.class);
    }
}
