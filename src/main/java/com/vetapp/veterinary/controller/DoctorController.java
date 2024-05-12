package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.DoctorService;
import com.vetapp.veterinary.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> findAll(){
        return this.doctorService.findAll();

    }
}
