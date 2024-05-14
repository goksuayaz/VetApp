package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    AvailableDate findById(long id);

    AvailableDate findByDoctorIdAvailableDays(Long doctorId, LocalDate appointmentDate);



}
