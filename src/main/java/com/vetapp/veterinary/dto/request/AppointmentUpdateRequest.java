package com.vetapp.veterinary.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {

    @Positive(message = "Appointment id must be positive")
    @NotNull(message = "Appointment id can not be null")
    private long id;


    @NotNull(message = "Appointment Date can not be empty")
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "Doctor id can not be empty or null")
    @Positive(message = "Doctor id must be positive")
    private long doctorId;

    @NotNull(message = "Animal id can not be empty or null")
    @Positive(message = "Animal id must be positive")
    private long animalId;
}

