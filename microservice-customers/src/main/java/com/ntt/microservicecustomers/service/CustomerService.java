package com.ntt.microservicecustomers.service;

import com.ntt.microservicecustomers.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Optional<Customer> findByDocumentNumberAndCustomerType_Id(String documentNumber, Long customerTypeId);

    Customer save(Customer customer);

    boolean existsByDocumentNumberAndCustomerType_Id(String documentNumber, Long customerTypeId);

    void deleteById(Long id);

}
