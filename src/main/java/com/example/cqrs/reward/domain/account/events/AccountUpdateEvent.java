package com.example.cqrs.reward.domain.account.events;

import com.example.cqrs.reward.entity.Order;
import lombok.Value;
import org.axonframework.modelling.command.AggregateIdentifier;

/**
 * @author albert
 */
@Value
public class AccountUpdateEvent implements AccountEvent {
	@AggregateIdentifier
	private final String id;

	private final Order order;
}
