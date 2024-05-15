package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AppointmentService;
import com.vetapp.veterinary.entity.Appointment;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.repository.AppointmentRepository;
import com.vetapp.veterinary.repository.AvailableDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AvailableDateRepository availableDateRepository;

    @Override
    public Appointment getByID(long id) {
        if (this.appointmentRepository.findById(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            return this.appointmentRepository.findById(id);
        }
    }

    @Override
    public Appointment save(Appointment appointment) {
        LocalDateTime appointmentDate = appointment.getAppointmentDate();
        Long doctorID = appointment.getDoctor().getId();
        AvailableDate availableDate = availableDateRepository.findByDoctorIdAndAvailableDays(doctorID,appointmentDate.toLocalDate());

        if (availableDate != null && isApointmentExistsOnDate(doctorID,appointmentDate)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            //throw new IllegalStateException("Doctor is not available!");
        }else {
            return this.appointmentRepository.save(appointment);
        }
    }

    @Override
    public String delete(long id) {
        if (this.appointmentRepository.findById(id) == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            this.appointmentRepository.delete(this.getByID(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public Appointment update(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getId());
        if (existingAppointment==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setDoctor(appointment.getDoctor());
            existingAppointment.setAnimal(appointment.getAnimal());
            return this.appointmentRepository.save(appointment);
        }

    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByAnimalIdBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Long animal_id) {
        return this.appointmentRepository.findByAppointmentDateBetweenAnimalId(startDate,endDate,animal_id);
    }

    @Override
    public List<Appointment> findByDoctorIdBetweenDates(LocalDateTime startDate, LocalDateTime endDate, Long doctor_id) {
        return this.appointmentRepository.findByAppointmentDateBetweenDoctorId(startDate,endDate,doctor_id);
    }

    private boolean isApointmentExistsOnDate(Long doctorId,LocalDateTime appointmentDate){
        return  appointmentRepository.existByDoctorIdAppointmentDate(doctorId,appointmentDate);
    }


}


