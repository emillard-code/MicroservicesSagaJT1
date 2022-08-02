package com.project.controller;

import com.project.dto.OrderRequestDTO;
import com.project.entity.PurchaseOrder;
import com.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO orderRequestDto) {

        return orderService.createOrder(orderRequestDto);

    }

    @GetMapping
    public List<PurchaseOrder> getOrders(){

        return orderService.getAllOrders();

    }

}