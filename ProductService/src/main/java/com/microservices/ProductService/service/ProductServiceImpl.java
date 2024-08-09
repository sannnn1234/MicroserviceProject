package com.microservices.ProductService.service;

import com.microservices.ProductService.entity.Product;
import com.microservices.ProductService.exception.ProductServiceCustomException;
import com.microservices.ProductService.model.ProductRequest;
import com.microservices.ProductService.model.ProductResponse;
import com.microservices.ProductService.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.beans.BeanUtils.*;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product..");
        Product product =
                Product.builder()
                        .productName(productRequest.getName())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                        .build();
        System.out.println(product);
        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }


    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for productId: {}", productId);

        Product product
                = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                = new ProductResponse();
        copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }
}
