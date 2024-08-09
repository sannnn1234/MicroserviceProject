package com.microservice.OrderService.controller;

import com.microservice.OrderService.model.OrderRequest;
import com.microservice.OrderService.model.OrderResponse;
import com.microservice.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/placedOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> orderDetails(@PathVariable long orderId){
        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);

    }

}
