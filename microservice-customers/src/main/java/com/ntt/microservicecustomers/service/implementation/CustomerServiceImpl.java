package com.ntt.microservicecustomers.service.implementation;

import com.ntt.microservicecustomers.model.Customer;
import com.ntt.microservicecustomers.repository.CustomerRepository;
import com.ntt.microservicecustomers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByDocumentNumberAndCustomerType_Id(String documentNumber, Long customerTypeId) {
        return customerRepository.findByDocumentNumberAndCustomerType_Id(documentNumber, customerTypeId);
    }

    @Override
    @Transactional()
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDocumentNumberAndCustomerType_Id(String documentNumber, Long customerTypeId) {
        return customerRepository.existsByDocumentNumberAndCustomerType_Id(documentNumber, customerTypeId);
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
