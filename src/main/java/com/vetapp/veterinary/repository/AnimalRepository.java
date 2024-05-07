package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findById(long id);



}
