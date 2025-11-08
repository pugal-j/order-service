package com.oms.ecommerce.ordermanagement.orderservice.service;

import com.oms.ecommerce.ordermanagement.orderservice.dto.OrderRequest;
import com.oms.ecommerce.ordermanagement.orderservice.entity.Order;
import com.oms.ecommerce.ordermanagement.orderservice.entity.OrderItem;
import com.oms.ecommerce.ordermanagement.orderservice.event.OrderCreatedEvent;
import com.oms.ecommerce.ordermanagement.orderservice.event.OrderEventPublisher;
import com.oms.ecommerce.ordermanagement.orderservice.exception.OrderNotFoundException;
import com.oms.ecommerce.ordermanagement.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEventPublisher eventPublisher;

    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        var items = request.getItems().stream()
                .map(item -> OrderItem.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        order.setItems(items);
        order.setTotalAmount(items.stream()
                .map(i -> i.getPrice().multiply(new java.math.BigDecimal(i.getQuantity())))
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));

        Order savedOrder = orderRepository.save(order);

        // Publish domain event
        OrderCreatedEvent event = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getCustomerId(), savedOrder.getTotalAmount());
        eventPublisher.publish(event);

        return savedOrder;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void updateStatus(Long id, String status) {
        Order order = getOrder(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
}