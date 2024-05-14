package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(long id);
    Customer findByName(String name);

}
