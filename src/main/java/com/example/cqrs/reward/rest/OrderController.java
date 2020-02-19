package com.example.cqrs.reward.rest;

import com.example.cqrs.reward.domain.order.commands.CreateOrderCommand;
import com.example.cqrs.reward.entity.Order;
import com.example.cqrs.reward.query.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final OrderRepository orderRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    private final CommandGateway commandGateway;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrder( @RequestBody @Valid Order order) {
        commandGateway.send(new CreateOrderCommand(order));
    }
}
