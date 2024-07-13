package com.wideedu.ordercartspring.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    private final List<OrderItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(long productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void clear() {
        items.clear();
    }

    public boolean updateItemQuantity(long productId, int quantity) {
        for (OrderItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                return true;
            }
        }
        return false;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (OrderItem item : items) {
            totalAmount += item.getTotalPrice();
        }
        return totalAmount;
    }
}
