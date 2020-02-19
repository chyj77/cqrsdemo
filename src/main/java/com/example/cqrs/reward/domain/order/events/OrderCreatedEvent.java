package com.example.cqrs.reward.domain.order.events;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;

/**
 * @author albert
 */
@Value
public class OrderCreatedEvent implements OrderEvent {

	private final int id;

	private final Order order;
}
