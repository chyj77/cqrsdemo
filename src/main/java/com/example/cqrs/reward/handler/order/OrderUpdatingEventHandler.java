package com.example.cqrs.reward.handler.order;

import com.example.cqrs.reward.domain.account.commands.CreateAccountCommand;
import com.example.cqrs.reward.domain.order.events.OrderCreatedEvent;
import com.example.cqrs.reward.domain.order.events.OrderUpdateEvent;
import com.example.cqrs.reward.entity.Order;
import com.example.cqrs.reward.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author albert
 */
@Component
@Slf4j
public class OrderUpdatingEventHandler {

    @Resource
    private  OrderRepository orderRepository;
    @Resource
    private CommandGateway commandGateway;

    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    @EventHandler
    void on(OrderCreatedEvent event)  {
        log.info("EventHandler OrderCreatedEvent ");
        Order order = event.getOrder();
        List<OrderItem> orderItemSet = order.getOrderItemSet();
        for (OrderItem orderItem:orderItemSet){
            orderItem.setOrder(order);
            orderItem.setOrderitemId(UUID.randomUUID().toString());
        }
        orderRepository.save(order);
    }

    @EventHandler
    void on(OrderUpdateEvent event) {
        Integer status = event.getStatus();
        Order order = orderRepository.findOneByOrderId(event.getId());
        order.setStatus(status);
        orderRepository.save(order);
        commandGateway.send(new CreateAccountCommand(identifierFactory.generateIdentifier(),order));
    }
/*
    @EventHandler
    void on(TaskTitleModifiedEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setTitle(event.getTitle());
			taskEntryRepository.save(task);
		});
    }

    @EventHandler
    void on(TaskStarredEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setStarred(true);
			taskEntryRepository.save(task);
		});
    }

    @EventHandler
    void on(TaskUnstarredEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setStarred(false);
			taskEntryRepository.save(task);
		});
    }
    */

}
