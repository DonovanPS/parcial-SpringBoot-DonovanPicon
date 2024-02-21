package com.uptc.parcialSpringBootDonovanPicon.services;

import com.uptc.parcialSpringBootDonovanPicon.entities.Product;
import com.uptc.parcialSpringBootDonovanPicon.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }

    public Product updateStockAndGetPrice(Integer id, Integer stock) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setStock(product.getStock());
            // Obtener el precio del producto de la base de datos y establecerlo en el producto
            Double price = product.getPrice();
            // También obtén el nombre del producto
            String name = product.getName();
            // Guardar el producto actualizado en la base de datos
            product = this.productRepository.save(product);
            // Establecer el precio y el nombre en el producto devuelto
            product.setPrice(price);
            product.setName(name);
            return product;
        }
        return null;
    }



}
