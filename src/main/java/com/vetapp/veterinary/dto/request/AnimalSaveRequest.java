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
public class AnimalSaveRequest {

    @NotNull(message = "Animal can not be empty")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;

    @NotNull(message = "Customer id can not be null or empty")
    @Positive(message = "Customer id must be positive")
    private long customerId;

}



