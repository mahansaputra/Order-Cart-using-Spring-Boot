package com.wideedu.ordercartspring.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private int quantity;
    private double totalPrice;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
