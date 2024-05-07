package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findById(long id);
}
