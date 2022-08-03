package com.project.event;

import com.project.dto.OrderRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OrderEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private OrderRequestDTO orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequestDTO orderRequestDto, OrderStatus orderStatus) {

        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;

    }

    @Override
    public UUID getEventId() {

        return eventId;

    }

    @Override
    public Date getDate() {

        return eventDate;

    }

}