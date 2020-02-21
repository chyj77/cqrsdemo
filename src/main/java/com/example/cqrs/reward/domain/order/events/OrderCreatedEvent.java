package com.example.cqrs.reward.domain.order.events;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author albert
 */
@Value
public class OrderCreatedEvent implements OrderEvent {
	@AggregateIdentifier
	private final String id;

	private final Order order;
}
