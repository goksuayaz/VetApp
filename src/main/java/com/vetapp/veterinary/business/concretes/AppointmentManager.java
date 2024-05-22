package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.IAppointmentService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Appointment;
import com.vetapp.veterinary.repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    @Override
    public Appointment get(Long id) {
        return this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        return null;
    }


    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepository.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> getByDoctorId(Long doctorId) {
        if (doctorId == null || doctorId <= 0){
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
        return this.appointmentRepository.findByDoctorId(doctorId);

    }



    @Override
    public List<Appointment> getByAnimalId(Long animalId) {
        if (animalId == null || animalId <= 0){
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
        return this.appointmentRepository.findByAnimalId(animalId);
    }


    @Override
    public List<Appointment> getAppointmentsByDateRange(String startDate, String endDate) {
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateTimeBetween(startDateTime, endDateTime);
        return appointments;
    }
}