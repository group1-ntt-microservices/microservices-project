package com.ntt.microservicecustomers.controller;

import com.ntt.microservicecustomers.model.CustomerType;
import com.ntt.microservicecustomers.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer-type")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    @GetMapping()
    public ResponseEntity<List<CustomerType>> findAll() {
        List<CustomerType> customerTypes = customerTypeService.findAll();
        return customerTypes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customerTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerType> findById(@PathVariable Long id) {
        Optional<CustomerType> optionalCustomerType = customerTypeService.findById(id);
        return optionalCustomerType.map(
                customerType -> ResponseEntity.ok().body(customerType)).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping()
    public ResponseEntity<CustomerType> save(@RequestBody CustomerType customerType) {
        CustomerType savedCustomerType = customerTypeService.save(customerType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<CustomerType> optionalCustomerType = customerTypeService.findById(id);
        return optionalCustomerType.map(
                customerType -> {
                    customerTypeService.deleteById(customerType.getId());
                    return ResponseEntity.noContent().build();
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
