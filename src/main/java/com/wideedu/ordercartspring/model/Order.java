package com.wideedu.ordercartspring.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private double totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(long id, long userId, double totalAmount, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Order(List<OrderItem> items, double totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }

//    private double calculateTotalAmount() {
//        totalAmount = 0;
//        for (OrderItem item : items) {
//            totalAmount += item.getTotalPrice();
//        }
//        return totalAmount;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
