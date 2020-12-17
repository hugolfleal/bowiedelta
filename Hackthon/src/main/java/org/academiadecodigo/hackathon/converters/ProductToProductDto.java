package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.ProductDto;
import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.academiadecodigo.hackathon.persistence.model.product.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDto extends AbstractConverter<Product, ProductDto> {


    @Override
    public ProductDto convert(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setProductType(product.getProductType().toString());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());

        return productDto;
    }
}
