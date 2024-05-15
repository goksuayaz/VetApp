package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.CustomerService;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll(){
        return this.customerService.findAll();

    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer finByID(@PathVariable("id") long id){
        return this.customerService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findByName(@PathVariable("name") String name){
        return this.customerService.findByName(name);
    }

    @GetMapping("/getPets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getPets(@PathVariable("id") long id){
        return customerService.findAnimalByCustomerId(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Customer update(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        return this.customerService.delete((long) id);
    }


}










