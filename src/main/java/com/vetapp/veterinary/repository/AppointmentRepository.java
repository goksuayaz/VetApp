package com.vetapp.veterinary.repository;

import com.vetapp.veterinary.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findById(long id);

    List<Appointment> findByAppointmentDateBetweenAnimalId(LocalDateTime startDate, LocalDateTime finishDate, Long animal_id);
    List<Appointment> findByAppointmentDateBetweenDoctorId(LocalDateTime startDate, LocalDateTime finishDate, Long animal_id);

    boolean existByDoctorIdAppointmentDate(Long doctorId, LocalDateTime localDateTime);

}
