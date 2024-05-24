package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);


}
