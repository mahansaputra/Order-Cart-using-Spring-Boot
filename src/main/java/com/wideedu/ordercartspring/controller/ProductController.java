package com.wideedu.ordercartspring.controller;

import com.wideedu.ordercartspring.dto.AddToCartDTO;
import com.wideedu.ordercartspring.model.Cart;
import com.wideedu.ordercartspring.model.OrderItem;
import com.wideedu.ordercartspring.model.Product;
import com.wideedu.ordercartspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final Cart cart;

    @Autowired
    public ProductController(ProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size) {
        return productService.getProducts(page, size);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return  productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/add-to-cart")
    public void addProductToCart(@RequestBody AddToCartDTO addToCartDTO) {
        if(addToCartDTO.getQuantity() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity cannot be less than 1");
        }
        long productId = addToCartDTO.getProductId();
        int quantity = addToCartDTO.getQuantity();

        Product product = productService.getProductById(productId);
        boolean productExistsInCart = cart.updateItemQuantity(productId, quantity);

        if (!productExistsInCart) {
            cart.addItem(new OrderItem(product,quantity));
        }
    }
}
