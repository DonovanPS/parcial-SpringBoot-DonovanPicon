package com.uptc.parcialSpringBootDonovanPicon.repositories;

import com.uptc.parcialSpringBootDonovanPicon.entities.Customer;
import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
   }
