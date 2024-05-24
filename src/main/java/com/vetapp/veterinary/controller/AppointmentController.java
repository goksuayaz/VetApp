package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.IAppointmentService;
import com.vetapp.veterinary.business.concretes.AvailableDateManager;
import com.vetapp.veterinary.core.config.modelMapper.IModelMapperService;
import com.vetapp.veterinary.core.result.Result;
import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.core.utilies.ResultHelper;
import com.vetapp.veterinary.dto.request.AppointmentSaveRequest;
import com.vetapp.veterinary.dto.response.AppointmentResponse;
import com.vetapp.veterinary.entity.Appointment;
import com.vetapp.veterinary.entity.Customer;
import com.vetapp.veterinary.repository.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/appointments")

public class AppointmentController {

    private final IAppointmentService appointmentService;

    private final AppointmentRepository appointmentRepository;

    private final AvailableDateManager availableDateManager;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService, AppointmentRepository appointmentRepository, AvailableDateManager availableDateManager, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.availableDateManager = availableDateManager;
        this.modelMapper = modelMapper;
    }


    //Endpoint that retrieves appointments by Doctor ID
    @GetMapping("/filter/doctor/{doctorId}")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorId(@PathVariable("doctorId") long doctorId) {

        List<Appointment> appointments = appointmentService.getByDoctorId(doctorId);

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @GetMapping("/appointmentsByDateAndDoctor")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorAndDateRange(doctorId, startDateTime, endDateTime);

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(appointmentResponses);
    }


    //Endpoint that retrieves appointments by Animal ID

    /*
    @GetMapping("/filter/animal/{animalId}")
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalId(@PathVariable("animalId") long animalId) {

        List<Appointment> appointments = appointmentService.getByAnimalId(animalId);

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }
*/

    // Endpoint that creates a new appointment record
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest ){
        LocalDate availableDate = appointmentSaveRequest.getAppointmentDateTime().toLocalDate();

        this.availableDateManager.availableDoctor(appointmentSaveRequest.getDoctorId(), availableDate);

        // Check if there is already an appointment at the given date and time
        this.appointmentExists(appointmentSaveRequest.getDoctorId(), appointmentSaveRequest.getAppointmentDateTime());

        // Converts AppointmentSaveRequest to Appointment class and saves it
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);

        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment,AppointmentResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get (@PathVariable("id") Long id) {

        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }


    //Endpoint that retrieves appointments within a specific date range

    /*
    @GetMapping("/filter/date")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        List<Appointment> appointments = appointmentService.getAppointmentsByDateRange(startDate, endDate);

        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }
*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        this.appointmentService.delete(id);
        return ResultHelper.Ok();
    }

    void appointmentExists(Long doctorId, LocalDateTime appointmentDateTime) {
        LocalDateTime startDateTime = appointmentDateTime.withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDateTime = startDateTime.plusHours(1); // Appointment duration assumed to be 1 hour

        // Is there another appointment within the specified time range to check?
        boolean doctorAvailable = this.appointmentRepository
                .existsByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);

        if (doctorAvailable) {
            throw new IllegalArgumentException("The doctor already has an appointment within the selected time range.");
        }
    }


}


