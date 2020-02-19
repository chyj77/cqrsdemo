package com.example.cqrs.reward.query.order;

import com.example.cqrs.reward.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author albert
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
