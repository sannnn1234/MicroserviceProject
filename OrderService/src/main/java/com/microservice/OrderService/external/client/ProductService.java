package com.microservice.OrderService.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name="PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceQuantity/{productId}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("productId") long productId,
                                               @RequestParam long quantity);
}
