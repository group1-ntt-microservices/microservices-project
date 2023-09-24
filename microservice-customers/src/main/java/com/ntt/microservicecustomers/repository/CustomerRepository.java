package com.ntt.microservicecustomers.repository;

import com.ntt.microservicecustomers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByDocumentNumberAndCustomerType_Id(String documentNumber, Long customerTypeId);
}
