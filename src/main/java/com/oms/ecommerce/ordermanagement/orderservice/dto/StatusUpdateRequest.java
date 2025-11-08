package com.oms.ecommerce.ordermanagement.orderservice.dto;

import com.oms.ecommerce.ordermanagement.orderservice.dto.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusUpdateRequest {
    private OrderStatus status;
}