package com.example.cqrs.reward.domain.order;

import com.example.cqrs.reward.domain.account.commands.CreateAccountCommand;
import com.example.cqrs.reward.domain.order.commands.CreateOrderCommand;
import com.example.cqrs.reward.domain.order.commands.UpdateOrderCommand;
import com.example.cqrs.reward.domain.order.events.OrderCreatedEvent;
import com.example.cqrs.reward.domain.order.events.OrderUpdateEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.ApplyMore;
import org.axonframework.spring.stereotype.Aggregate;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
@Slf4j
public class OrderDomain {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = -5977984483620451637L;

    @AggregateIdentifier
    private String id;

    @NotNull
    private int status;

    /**
     * Creates a new Task.
     *
     * @param command create Task
     */
    @CommandHandler
    public OrderDomain(CreateOrderCommand command) {
        log.info("CommandHandler CreateOrderCommand ");
        apply(new OrderCreatedEvent(command.getId(),command.getOrder()));
    }

    @EventSourcingHandler
    void on(OrderCreatedEvent event) {
        log.info("EventSourcingHandler OrderCreatedEvent ");
        this.id = event.getId();
    }
    @CommandHandler
    void on(UpdateOrderCommand command) {
        apply(new OrderUpdateEvent(command.getId(),command.getStatus()));
    }
    @EventSourcingHandler
    void on(OrderUpdateEvent event) {
        this.id = event.getId();
    }
}
