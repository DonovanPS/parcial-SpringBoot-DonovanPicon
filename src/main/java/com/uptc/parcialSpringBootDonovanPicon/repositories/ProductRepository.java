package com.uptc.parcialSpringBootDonovanPicon.repositories;

import com.uptc.parcialSpringBootDonovanPicon.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
