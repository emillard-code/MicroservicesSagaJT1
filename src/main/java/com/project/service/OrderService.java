package com.project.service;

import com.project.dto.OrderRequestDTO;
import com.project.entity.PurchaseOrder;
import com.project.event.OrderStatus;
import com.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDto) {

        PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());
        //produce kafka event with status ORDER_CREATED


        return order;

    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDTO dto) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(dto.getAmount());

        return purchaseOrder;

    }

}