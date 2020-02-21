package com.example.cqrs.reward.domain.account.commands;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * @author albert
 */
@Value
public class UpdateAccountCommand {

	@TargetAggregateIdentifier
	private final String id;

	@NotNull
	private final Order order;

}
