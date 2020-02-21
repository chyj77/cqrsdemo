package com.example.cqrs.reward.rest;

import com.example.cqrs.reward.domain.order.commands.CreateOrderCommand;
import com.example.cqrs.reward.domain.order.commands.UpdateOrderCommand;
import com.example.cqrs.reward.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Log4j2
@RequestMapping(value = "/order")
public class OrderController {
    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    @Resource
    private  CommandGateway commandGateway;

    @RequestMapping(value = "/create", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createOrder( @RequestBody  Order order) {
        String orderId = identifierFactory.generateIdentifier();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        commandGateway.send(new CreateOrderCommand(orderId,order));
        log.info("true done");
        return "OK";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateOrder(@RequestParam String orderId,@RequestParam int status) {
        commandGateway.send(new UpdateOrderCommand(orderId,status));
        return "OK";
    }

}
