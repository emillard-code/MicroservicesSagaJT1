package com.project.service;

import com.project.dto.OrderRequestDTO;
import com.project.event.OrderEvent;
import com.project.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Sinks;

public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDTO orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);

    }

}