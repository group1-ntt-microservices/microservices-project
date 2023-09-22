package com.ntt.microservicecustomers.service;

import com.ntt.microservicecustomers.model.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerTypeService {

    List<CustomerType> findAll();

    Optional<CustomerType> findById(Long id);

    CustomerType save(CustomerType customerType);

    void deleteById(Long id);
}
