package com.wideedu.ordercartspring.repository;

import com.wideedu.ordercartspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
