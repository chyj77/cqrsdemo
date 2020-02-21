package com.example.cqrs.reward.handler.order;

import com.example.cqrs.reward.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author albert
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOneById(String orderId);
}
