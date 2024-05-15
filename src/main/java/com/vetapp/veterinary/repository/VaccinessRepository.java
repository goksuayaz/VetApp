package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Vacciness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface VaccinessRepository extends JpaRepository<Vacciness,Long> {

        Vacciness findById(long id);
    }



