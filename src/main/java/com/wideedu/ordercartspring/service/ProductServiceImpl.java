package com.wideedu.ordercartspring.service;

import com.wideedu.ordercartspring.model.Product;
import com.wideedu.ordercartspring.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(long id, Product updatedProduct) {
        Product oldProduct = productRepository.findById(id).orElse(null);
        if (oldProduct != null) {
            oldProduct.setName(updatedProduct.getName());
            oldProduct.setType(updatedProduct.getType());
            oldProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(oldProduct);
        }
        return null;
    }
}
