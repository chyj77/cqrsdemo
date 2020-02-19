package com.example.cqrs.reward.domain.order.commands;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * @author albert
 */
@Value
public class CreateOrderCommand {

	@NotNull
	private final Order order;

}
