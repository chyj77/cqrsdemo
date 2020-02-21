package com.example.cqrs.reward.handler.order;

import com.example.cqrs.reward.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author albert
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOneByOrderId(String orderId);
}
