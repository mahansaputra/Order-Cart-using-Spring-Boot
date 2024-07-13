package com.wideedu.ordercartspring.service;

import com.wideedu.ordercartspring.model.Order;
import com.wideedu.ordercartspring.model.OrderItem;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order, List<OrderItem> orderItems);
    Order getOrderById(Long id);
    void deleteOrder(Long id);
    List<Order> findAll();
}
