package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.AnimalService;
import com.vetapp.veterinary.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAll(){
        return this.animalService.findAll();

    }




}
