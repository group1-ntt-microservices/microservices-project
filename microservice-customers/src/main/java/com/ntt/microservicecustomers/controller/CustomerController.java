package com.ntt.microservicecustomers.controller;

import com.ntt.microservicecustomers.dto.CustomerResponseDto;
import com.ntt.microservicecustomers.mapper.CustomerMapper;
import com.ntt.microservicecustomers.model.Customer;
import com.ntt.microservicecustomers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerResponseMapper;

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customerResponseMapper.toCustomerResponseDtoList(customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(
                        customer -> ResponseEntity.ok().body(customerResponseMapper.toCustomerResponseDto(customer))).
                orElseGet(() -> ResponseEntity.notFound().build()
                );
    }

    @PostMapping("/")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(
                customer -> {
                    customerService.deleteById(customer.getId());
                    return ResponseEntity.noContent().build();
                }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.notFound().build();
    }
}
