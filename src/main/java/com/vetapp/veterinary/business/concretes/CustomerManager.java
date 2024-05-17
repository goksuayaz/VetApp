package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.ICustomerService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Customer;
import com.vetapp.veterinary.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements ICustomerService {


    private final CustomerRepository customerRepository;


    public CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }


    @Override
    public Customer get(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Customer> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepository.findAll(pageable);
    }


    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepository.save(customer);
    }


    @Override
    public boolean delete(long id) {
        Customer customer = this.get(id);
        this.customerRepository.delete(customer);
        return true;
    }


    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
}



