package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.ProductDto;
import org.academiadecodigo.hackathon.persistence.model.product.*;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct extends AbstractConverter<ProductDto, Product> {


    @Override
    public Product convert(ProductDto productDto) {

        Product product ;

        ProductType productType = productDto.getProductType();

        switch (productType){
            case BUS: product = new BusProduct();
                break;
            case TRIP: product= new TripProduct();
                break;
                default: product = new GamingProduct();
        }



        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());

        return product;
    }
}