package com.project.event;

import com.project.dto.PaymentRequestDTO;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class PaymentEvent implements Event {

    private UUID eventId=UUID.randomUUID();
    private Date eventDate = new Date();
    private PaymentRequestDTO paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequestDTO paymentRequestDto, PaymentStatus paymentStatus) {

        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;

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