package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.CustomerService;
import com.vetapp.veterinary.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findByName(String name) {
        return null;
    }
}
