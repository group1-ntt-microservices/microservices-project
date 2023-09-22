package com.ntt.microservicecustomers.repository;

import com.ntt.microservicecustomers.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
}
