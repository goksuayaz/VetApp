package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.VaccineService;
import com.vetapp.veterinary.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;


    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll(){
        return this.vaccineService.findAll();

    }

}
