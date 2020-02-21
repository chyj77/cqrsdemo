package com.example.cqrs.reward.domain.account;

import com.example.cqrs.reward.domain.account.commands.CreateAccountCommand;
import com.example.cqrs.reward.domain.account.commands.UpdateAccountCommand;
import com.example.cqrs.reward.domain.account.events.AccountCreatedEvent;
import com.example.cqrs.reward.domain.account.events.AccountUpdateEvent;
import com.example.cqrs.reward.domain.order.commands.CreateOrderCommand;
import com.example.cqrs.reward.domain.order.commands.UpdateOrderCommand;
import com.example.cqrs.reward.domain.order.events.OrderCreatedEvent;
import com.example.cqrs.reward.domain.order.events.OrderUpdateEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
@Slf4j
public class AccountAggregate {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = -5977984483620459637L;

    @AggregateIdentifier
    private String id;

    /**
     * Creates a new Task.
     *
     * @param command create Task
     */
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        log.info("CommandHandler CreateAccountCommand ");
        apply(new AccountCreatedEvent(command.getId(),command.getOrder()));
    }

    @EventSourcingHandler
    void on(AccountCreatedEvent event) {
        log.info("EventSourcingHandler AccountCreatedEvent ");
        this.id = event.getId();
    }
    @CommandHandler
    void on(UpdateAccountCommand command) {
        apply(new AccountUpdateEvent(command.getId(),command.getOrder()));
    }
    @EventSourcingHandler
    void on(AccountUpdateEvent event) {
        this.id = event.getId();
    }
}
