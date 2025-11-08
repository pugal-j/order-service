package com.oms.ecommerce.ordermanagement.orderservice.controller;

import com.oms.ecommerce.ordermanagement.orderservice.dto.OrderRequest;
import com.oms.ecommerce.ordermanagement.orderservice.dto.StatusUpdateRequest;
import com.oms.ecommerce.ordermanagement.orderservice.entity.Order;
import com.oms.ecommerce.ordermanagement.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        orderService.updateStatus(id, request.getStatus().toString());
        return ResponseEntity.noContent().build();
    }
}
