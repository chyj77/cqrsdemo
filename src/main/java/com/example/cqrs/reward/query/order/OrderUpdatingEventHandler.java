package com.example.cqrs.reward.query.order;

import com.example.cqrs.reward.domain.order.events.OrderCreatedEvent;
import com.example.cqrs.reward.entity.Order;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author albert
 */
@Component
public class OrderUpdatingEventHandler {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderUpdatingEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    void on(OrderCreatedEvent event) {
        Order order = event.getOrder();
        orderRepository.save(order);
    }
/*
    @EventHandler
    void on(TaskCompletedEvent event) {
        taskEntryRepository.findById(event.getId()).ifPresent(task -> {
            task.setCompleted(true);
            taskEntryRepository.save(task);
        });
    }

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
