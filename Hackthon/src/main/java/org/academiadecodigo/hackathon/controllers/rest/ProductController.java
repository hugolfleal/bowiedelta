package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.AddressDto;
import org.academiadecodigo.hackathon.command.ProductDto;
import org.academiadecodigo.hackathon.converters.ProductDtoToProduct;
import org.academiadecodigo.hackathon.converters.ProductToProductDto;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.academiadecodigo.hackathon.persistence.model.product.Product;
import org.academiadecodigo.hackathon.persistence.model.product.ProductType;
import org.academiadecodigo.hackathon.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private ProductDtoToProduct productDtoToProduct;
    private ProductToProductDto productToProductDto;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductDtoToProduct(ProductDtoToProduct productDtoToProduct) {
        this.productDtoToProduct = productDtoToProduct;
    }

    @Autowired
    public void setProductToProductDto(ProductToProductDto productToProductDto) {
        this.productToProductDto = productToProductDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id){

        Product product = productService.get(id);
        ProductDto productDto = productToProductDto.convert(product);


        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto,
                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productDtoToProduct.convert(productDto);
        Product savedProduct = productService.save(product);

        productDto =productToProductDto.convert(savedProduct);

        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
    public ResponseEntity<ProductDto> editProduct(@Valid @RequestBody ProductDto productDto,
                                               BindingResult bindingResult,
                                               @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Product product = productService.get(id);
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());

            Product savedProduct = productService.save(product);
            productDto = productToProductDto.convert(savedProduct);
            return new ResponseEntity<>(productDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        try {
            productService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
