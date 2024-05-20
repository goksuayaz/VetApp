package com.vetapp.veterinary.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {

    @NotNull(message = "Appointment Date and Time can not be empty")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentDateTime;

    @NotNull(message = "Doctor id can not be empty or null")
    @Positive(message = "Doctor id must be positive")
    private Long doctorId;
    @NotNull(message = "Animal id can not be empty or null")
    @Positive(message = "Animal id must be positive")
    private Long animalId;
}

