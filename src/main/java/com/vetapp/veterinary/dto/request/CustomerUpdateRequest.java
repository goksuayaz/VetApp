package com.vetapp.veterinary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @Positive(message = "Customer id must be positive")
    @NotNull(message = "Customer id can not be null")
    private long id;

    @NotNull(message = "Customer name can not be empty")
    private String name;
    private String phone;

    @Email(message = "Invalid e-mail format")
    private String mail;
    private String address;
    private String city;

}

