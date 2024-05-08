package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Long id);

    Customer save(Customer customer);

    String delete(Long id);

    Customer update(Customer customer);

    List<Customer> findAll();

    Customer findByName(String name);
}
