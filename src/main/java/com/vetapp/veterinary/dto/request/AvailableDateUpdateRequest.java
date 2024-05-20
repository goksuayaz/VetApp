package com.vetapp.veterinary.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {

    @Positive(message = "Id must be positive")
    @NotNull(message = "Id can not be null")
    private long id;

    @NotNull(message = "Available Date can not be empty")
    private LocalDate availableDate;

    @Positive(message = "Doctor id must be positive")
    @NotNull(message = "Doctor id can not be empty")
    private long doctorId;
}

