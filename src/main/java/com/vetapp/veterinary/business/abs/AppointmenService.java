package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Appointment;

import java.util.List;

public interface AppointmenService {

    Appointment save(Appointment appointment);

    String delete(Long id);

    Appointment update(Appointment appointment);

    Appointment getById(Long id);

    List<Appointment> findAll();



}
