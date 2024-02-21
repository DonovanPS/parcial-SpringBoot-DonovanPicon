package com.uptc.parcialSpringBootDonovanPicon.controller;

import com.uptc.parcialSpringBootDonovanPicon.entities.Customer;
import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import com.uptc.parcialSpringBootDonovanPicon.responses.ResponseHandler;
import com.uptc.parcialSpringBootDonovanPicon.services.CustomerService;
import com.uptc.parcialSpringBootDonovanPicon.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/customers"})
public class CustomerController {

    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public CustomerController(CustomerService customerService, SaleService saleService) {
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomers() {
        try {
            List<Customer> customers = this.customerService.getAllCustomers();
            return ResponseHandler.generateResponse("List of customers", HttpStatus.OK, customers);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getCustomerById(Integer id) {
        try {
            Customer customer = this.customerService.getCustomerById(id);
            return customer != null ? ResponseHandler.generateResponse("Customer found", HttpStatus.OK, customer) : ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, (Object) null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveCustomer(Customer customer) {
        try {
            return ResponseHandler.generateResponse("Customer saved", HttpStatus.OK, this.customerService.saveCustomer(customer));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Object> updateCustomer( @RequestParam Integer id, @RequestBody Customer customer){
        try {
            Customer customerUpdate = this.customerService.getCustomerById(id);

            if(customerUpdate != null) {
                customer.setId(id);
                return ResponseHandler.generateResponse("Customer updated", HttpStatus.OK, this.customerService.updateCustomer(customer));
            }else {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, (Object) null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> deleteCustomer(Integer id) {
        try {
            Customer customer = this.customerService.getCustomerById(id);

            if(customer != null){
                this.customerService.deleteCustomer(id);
                return ResponseHandler.generateResponse("Customer deleted", HttpStatus.OK, (Object) null);
            }else{
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, (Object) null);
            }

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, (Object) null);
        }
    }



}
