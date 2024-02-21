package com.uptc.parcialSpringBootDonovanPicon.services;

import com.uptc.parcialSpringBootDonovanPicon.entities.Product;
import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import com.uptc.parcialSpringBootDonovanPicon.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    final private SaleRepository saleRepository;
    final private ProductService productService;

    @Autowired
    public SaleService(SaleRepository saleRepository, ProductService productService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
    }

    public boolean validStock(Integer id, Integer quantity) {
        Product product = this.productService.getProductById(id);

        if (product != null) {
            return product.getStock() >= quantity;
        }
        return false;
    }

    public Product updateStock(Integer id, Integer quantity) {
        Product product = this.productService.getProductById(id);
        if (product != null) {
            product.setStock(product.getStock() - quantity);
            return this.productService.saveProduct(product);
        }
        return null;
    }

    public Sale saveSale(Sale sale) {
        return this.saleRepository.save(sale);
    }

    public List<Sale> getAllSales(){
        return this.saleRepository.findAll();
    }

    public Sale getSaleById(Integer id){
        return this.saleRepository.findById(id).orElse(null);
    }

    public List<Sale> getSalesByCustomerId(Integer customerId) {
        return saleRepository.findByCustomerId(customerId);
    }
}
