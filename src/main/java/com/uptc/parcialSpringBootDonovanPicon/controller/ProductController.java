package com.uptc.parcialSpringBootDonovanPicon.controller;

import com.uptc.parcialSpringBootDonovanPicon.entities.Product;
import com.uptc.parcialSpringBootDonovanPicon.responses.ResponseHandler;
import com.uptc.parcialSpringBootDonovanPicon.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/products"})
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<Product> products = this.productService.getAllProducts();
            return ResponseHandler.generateResponse("List of products", HttpStatus.OK, products);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(Product product) {
        try {
            return ResponseHandler.generateResponse("Product saved", HttpStatus.OK, this.productService.saveProduct(product));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }
}
