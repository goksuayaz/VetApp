package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByAnimalId(Long animalId);

    //Find appointments by doctor ID
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}

