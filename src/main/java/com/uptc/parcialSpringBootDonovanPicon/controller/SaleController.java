package com.uptc.parcialSpringBootDonovanPicon.controller;

import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import com.uptc.parcialSpringBootDonovanPicon.entities.Product;
import com.uptc.parcialSpringBootDonovanPicon.responses.ResponseHandler;
import com.uptc.parcialSpringBootDonovanPicon.services.ProductService;
import com.uptc.parcialSpringBootDonovanPicon.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;

    @Autowired
    public SaleController(SaleService saleService, ProductService productService) {
        this.saleService = saleService;
        this.productService = productService;
    }



    @GetMapping
    public ResponseEntity<Object> getAllSales() {
        try {
            List<Sale> sales = this.saleService.getAllSales();
            return ResponseHandler.generateResponse("List of sales", HttpStatus.OK, sales);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveSale(@RequestBody Sale sale) {
        try {
            List<Product> products = sale.getProducts();
            List<Product> productsWithInsufficientStock = new ArrayList<>();
            Double totalSale = 0.0;

            // Validar el stock de cada producto y calcular el total de la venta
            for (Product product : products) {
                boolean validStock = this.saleService.validStock(product.getId(), product.getStock());

                if (!validStock) {
                    productsWithInsufficientStock.add(product);
                } else {
                    // Actualizar el stock del producto y obtener el producto actualizado con su precio
                    Product updatedProduct = this.productService.updateStockAndGetPrice(product.getId(), product.getStock());
                    if (updatedProduct != null) {
                        totalSale += updatedProduct.getPrice() * product.getStock();
                    }
                }
            }

            // Si no hay productos con stock insuficiente, proceder con la venta
            if (productsWithInsufficientStock.isEmpty()) {
                // Iterar sobre los productos vendidos para disminuir el stock
                for (Product product : products) {
                    // Disminuir el stock del producto en la base de datos
                    this.saleService.updateStock(product.getId(), product.getStock());
                }

                // Establecer el total de la venta en la entidad Sale
                sale.setTotal(totalSale);

                // Guardar la venta en la base de datos
                return ResponseHandler.generateResponse("Sale saved", HttpStatus.OK, this.saleService.saveSale(sale));
            } else {
                StringBuilder errorMessage = new StringBuilder("Not enough stock for products: ");
                for (Product product : productsWithInsufficientStock) {
                    errorMessage.append(product.getName()).append(", ");
                }
                errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // Eliminar la última coma y espacio
                return ResponseHandler.generateResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST, (Object) null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getSaleById(Integer id){
        try {
            Sale sale = this.saleService.getSaleById(id);
            return sale != null ? ResponseHandler.generateResponse("Sale found", HttpStatus.OK, sale) : ResponseHandler.generateResponse("Sale not found", HttpStatus.NOT_FOUND, (Object) null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }



    @GetMapping("/customers/{customerId}/sales")
    public ResponseEntity<List<Sale>> getSalesByCustomerId(@PathVariable Integer customerId) {
        List<Sale> sales = saleService.getSalesByCustomerId(customerId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }


}
