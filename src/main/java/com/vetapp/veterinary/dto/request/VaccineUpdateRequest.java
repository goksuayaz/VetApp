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
public class VaccineUpdateRequest {

    @Positive(message = "Vaccine id must be positive")
    @NotNull(message = "Vaccine id can not be null")
    private Long id;

    @NotNull(message = "Vaccine name can not be empty or null")
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    @NotNull(message = "Animal id can not be null")
    @Positive(message = "Animal id must be positive")
    private Long animalId;
}

