package com.ntt.microservicecustomers.controller;

import com.ntt.microservicecustomers.dto.CustomerRequestDto;
import com.ntt.microservicecustomers.dto.CustomerResponseDto;
import com.ntt.microservicecustomers.mapper.CustomerMapper;
import com.ntt.microservicecustomers.model.Customer;
import com.ntt.microservicecustomers.model.CustomerType;
import com.ntt.microservicecustomers.service.CustomerService;
import com.ntt.microservicecustomers.service.CustomerTypeService;
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
    private CustomerTypeService customerTypeService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customerMapper.toCustomerResponseDtoList(customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(
                        customer -> ResponseEntity.ok().body(customerMapper.toCustomerResponseDto(customer))).
                orElseGet(() -> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/document")
    public ResponseEntity<CustomerResponseDto> findByDocumentNumber(
            @RequestParam("document") String documentNumber,
            @RequestParam("type") Long customerTypeId)
    {
        Optional<Customer> optionalCustomer = customerService.findByDocumentNumberAndCustomerType_Id(documentNumber, customerTypeId);
        return optionalCustomer.map(
                customer -> ResponseEntity.ok().body(customerMapper.toCustomerResponseDto(customer))
        ).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<List<CustomerResponseDto>> findByDocumentNumber(@PathVariable String documentNumber){
        List<Customer> customers = customerService.findByDocumentNumber(documentNumber);
        return customers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customerMapper.toCustomerResponseDtoList(customers));
    }


    @PostMapping("/")
    public ResponseEntity<CustomerResponseDto> save(@RequestBody CustomerRequestDto customerRequestDto) {
        Optional<CustomerType> customerTypeOptional = customerTypeService.findById(customerRequestDto.getCustomerTypeId());
        if (!customerTypeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (customerService.existsByDocumentNumberAndCustomerType_Id(customerRequestDto.getDocumentNumber(), customerRequestDto.getCustomerTypeId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Customer customer = customerMapper.toCustomer(customerRequestDto);
        customer.setCustomerType(customerTypeOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toCustomerResponseDto(customerService.save(customer)));
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
