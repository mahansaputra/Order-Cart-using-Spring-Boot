package com.wideedu.ordercartspring.repository;

import com.wideedu.ordercartspring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
