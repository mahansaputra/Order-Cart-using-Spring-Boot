package com.wideedu.ordercartspring.service;

import com.wideedu.ordercartspring.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    Product saveProduct(Product product);
    void deleteProduct(long id);
    Product updateProduct(long id, Product updatedProduct);
    List<Product> getAllProducts();
    Page<Product> getProducts(int page, int size);
}
