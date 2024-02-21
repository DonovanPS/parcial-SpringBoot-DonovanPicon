package com.uptc.parcialSpringBootDonovanPicon.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "date_sale")
    private LocalDate date_sale;

    @Column(name = "total")
    private Double total;

    @ManyToMany
    @JoinTable(name = "product_sale",
            joinColumns = {@JoinColumn(name = "sale_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Sale() {
    }

    public Sale(Integer id, LocalDate date_sale, Double total, List<Product> products, Customer customer) {
        this.id = id;
        this.date_sale = date_sale;
        this.total = total;
        this.products = products;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_sale() {
        return date_sale;
    }

    public void setDate_sale(LocalDate date_sale) {
        this.date_sale = date_sale;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
