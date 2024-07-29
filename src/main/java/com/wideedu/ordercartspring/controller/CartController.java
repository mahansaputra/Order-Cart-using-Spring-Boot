package com.wideedu.ordercartspring.controller;

import com.wideedu.ordercartspring.model.Cart;
import com.wideedu.ordercartspring.model.Order;
import com.wideedu.ordercartspring.model.OrderItem;
import com.wideedu.ordercartspring.model.User;
import com.wideedu.ordercartspring.service.OrderService;
import com.wideedu.ordercartspring.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final Cart cart;
    private final OrderService orderService;
    private final UserServiceImpl userService;

    public CartController(OrderService orderService, Cart cart, UserServiceImpl userService) {
        this.orderService = orderService;
        this.cart = cart;
        this.userService = userService;
    }

    @GetMapping
    public List<OrderItem> viewCart() {
        return cart.getItems();
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable long id) {
        cart.removeItem(id);
    }

    @PostMapping("/checkout")
    public void checkout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotalAmount(cart.getTotalAmount());

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItems.add(orderItem);
        }

        orderService.saveOrder(order, orderItems);

        cart.clear();
    }

}
