package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.dto.response.AppointmentResponse;
import com.vetapp.veterinary.entity.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);
    Appointment get(long id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update (Appointment appointment);
    boolean delete (long id);

    List<Appointment> getByDoctorId(Long doctorId);
    //List<Appointment> getByAnimalId(Long animalId);

    //List<Appointment> getAppointmentsByDateRange(String startDate, String endDate);


    List<Appointment> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

}

