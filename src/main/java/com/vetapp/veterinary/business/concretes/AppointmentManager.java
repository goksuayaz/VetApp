package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AppointmenService;
import com.vetapp.veterinary.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentManager implements AppointmenService {
    @Override
    public Appointment save(Appointment appointment) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    @Override
    public Appointment getById(Long id) {
        return null;
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }
}
