package com.ntt.microservicecustomers.repository;

import com.ntt.microservicecustomers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
