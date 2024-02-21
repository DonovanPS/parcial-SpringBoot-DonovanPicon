package com.uptc.parcialSpringBootDonovanPicon.services;

import com.uptc.parcialSpringBootDonovanPicon.entities.Customer;
import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import com.uptc.parcialSpringBootDonovanPicon.repositories.CustomerRepository;
import com.uptc.parcialSpringBootDonovanPicon.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;
    final private SaleRepository saleRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        this.customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public List<Sale> getSalesByCustomerId(Integer customerId) {
        return saleRepository.findByCustomerId(customerId);
    }

}
