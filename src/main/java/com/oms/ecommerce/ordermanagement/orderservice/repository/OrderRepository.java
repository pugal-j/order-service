package com.oms.ecommerce.ordermanagement.orderservice.repository;

import com.oms.ecommerce.ordermanagement.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

