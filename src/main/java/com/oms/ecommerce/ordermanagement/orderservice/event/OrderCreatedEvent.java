package com.oms.ecommerce.ordermanagement.orderservice.event;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderCreatedEvent {
    private Long orderId;
    private Integer customerId;
    private BigDecimal totalAmount;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, Integer customerId, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }
}