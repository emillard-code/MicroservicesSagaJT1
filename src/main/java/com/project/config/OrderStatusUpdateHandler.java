package com.project.config;

import com.project.dto.OrderRequestDTO;
import com.project.entity.PurchaseOrder;
import com.project.event.OrderStatus;
import com.project.event.PaymentStatus;
import com.project.repository.OrderRepository;
import com.project.service.OrderStatusPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {

        repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder) {

        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isPaymentComplete) {
            publisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
        }

    }

    public OrderRequestDTO convertEntityToDto(PurchaseOrder purchaseOrder) {

        OrderRequestDTO orderRequestDto = new OrderRequestDTO();
        orderRequestDto.setOrderId(purchaseOrder.getId());
        orderRequestDto.setUserId(purchaseOrder.getUserId());
        orderRequestDto.setAmount(purchaseOrder.getPrice());
        orderRequestDto.setProductId(purchaseOrder.getProductId());

        return orderRequestDto;

    }

}