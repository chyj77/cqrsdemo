package com.example.cqrs.reward.domain.order.events;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;
import org.axonframework.modelling.command.AggregateIdentifier;

/**
 * @author albert
 */
@Value
public class OrderUpdateEvent implements OrderEvent {
	@AggregateIdentifier
	private final String id;

	private final Order order;
}
