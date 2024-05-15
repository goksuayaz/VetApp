package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.AnimalService;
import com.vetapp.veterinary.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal getById(@PathVariable("id") long id){
        return this.animalService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal save(@RequestBody Animal animal){
        return this.animalService.save(animal);
    }



    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Animal update(@RequestBody Animal animal){
        return animalService.save(animal);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        return this.animalService.delete((long) id);
    }


}






