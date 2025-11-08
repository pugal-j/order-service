package com.oms.ecommerce.ordermanagement.orderservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public class OrderRequest {
    private Integer customerId;
    private List<OrderItemRequest> items;

    @Getter
    @Setter
    public static class OrderItemRequest {
        private Long productId;
        private int quantity;
        private BigDecimal price;
    }
}