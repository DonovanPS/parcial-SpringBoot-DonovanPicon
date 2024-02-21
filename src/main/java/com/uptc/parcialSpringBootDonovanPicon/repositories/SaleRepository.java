package com.uptc.parcialSpringBootDonovanPicon.repositories;

import com.uptc.parcialSpringBootDonovanPicon.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Query("SELECT s FROM Sale s WHERE s.customer.id = :customerId")
    List<Sale> findByCustomerId(@Param("customerId") Integer customerId);

}
