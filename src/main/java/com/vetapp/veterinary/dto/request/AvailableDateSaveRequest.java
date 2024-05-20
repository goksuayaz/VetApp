package com.vetapp.veterinary.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveRequest {

    @NotNull(message = "Available Date can not be empty")
    private LocalDate availableDate;

    @NotNull(message = "Doctor id can not be empty or null")
    @Positive(message = "Doctor id must be positive")
    private long doctorId;
}



