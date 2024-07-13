package com.wideedu.ordercartspring.service;

import com.wideedu.ordercartspring.model.Order;
import com.wideedu.ordercartspring.model.OrderItem;
import com.wideedu.ordercartspring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void saveOrder(Order order, List<OrderItem> orderItems) {
        for(OrderItem item: orderItems ){
            item.setOrder(order);
        }
        order.setItems(orderItems);

        orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
