package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment getById(Long id);
    Appointment save(Appointment appointment);
    String delete(Long id);
    Appointment update(Appointment appointment);

    List<Appointment> findAll();

    List<Appointment> findByAnimalIdBetweenDates(LocalDateTime startDate, LocalDateTime finishDate, Long animal_id);

    List<Appointment> findByDoctorIdBetweenDates(LocalDateTime startDate,LocalDateTime finishDate, Long animal_id);


}



