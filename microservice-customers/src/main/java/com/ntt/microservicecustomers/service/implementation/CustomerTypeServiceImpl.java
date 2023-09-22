package com.ntt.microservicecustomers.service.implementation;

import com.ntt.microservicecustomers.model.CustomerType;
import com.ntt.microservicecustomers.repository.CustomerTypeRepository;
import com.ntt.microservicecustomers.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Optional<CustomerType> findById(Long id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public CustomerType save(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }

    @Override
    public void deleteById(Long id) {
        customerTypeRepository.deleteById(id);
    }
}
