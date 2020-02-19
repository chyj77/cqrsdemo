package com.example.cqrs.reward.domain.order;

import com.example.cqrs.reward.domain.order.commands.CreateOrderCommand;
import com.example.cqrs.reward.domain.order.events.OrderCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class OrderDomain {

    /**
     * The constant serialVersionUID
     */
    private static final long serialVersionUID = -5977984483620451637L;

    @AggregateIdentifier
    private int id;

    @NotNull
    private int status;

    /**
     * Creates a new Task.
     *
     * @param command create Task
     */
    @CommandHandler
    public OrderDomain(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(id,command.getOrder()));
    }

    @EventSourcingHandler
    void on(OrderCreatedEvent event) {
        this.id = event.getId();
    }


}
