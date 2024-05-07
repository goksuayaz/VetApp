package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Vaccine findById(long id);

}
