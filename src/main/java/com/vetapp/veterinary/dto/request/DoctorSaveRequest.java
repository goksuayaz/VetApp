package com.vetapp.veterinary.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSaveRequest {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}

