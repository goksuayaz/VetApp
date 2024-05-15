package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.CustomerService;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.Customer;
import com.vetapp.veterinary.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getByID(int id) {
        if (this.customerRepository.findById(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            return this.customerRepository.findById(id);
        }
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public String delete(int id) {
        if (this.customerRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            this.customerRepository.delete(this.getByID(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public Customer update(Customer customer) {
        Customer existingCustomer = customerRepository.findById((int) customer.getId());
        if (existingCustomer==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingCustomer.setName(customer.getName());
            existingCustomer.setMail(customer.getMail());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPhone(customer.getPhone());
            return this.customerRepository.save(customer);
        }

    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByName(String name) {
        return this.customerRepository.findByName(name);
    }

    @Override
    public List<Animal> findAnimalByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer!=null){
            return customer.getAnimalList();
        }else {
            return Collections.emptyList();
        }
    }
}


}