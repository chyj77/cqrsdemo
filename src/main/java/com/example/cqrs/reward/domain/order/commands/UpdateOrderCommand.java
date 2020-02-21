package com.example.cqrs.reward.domain.order.commands;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * @author albert
 */
@Value
public class UpdateOrderCommand {

	@TargetAggregateIdentifier
	private final String id;

	@NotNull
	private final Order order;

}
