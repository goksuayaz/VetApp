package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    AvailableDate findById(long id);


}
