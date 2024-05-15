package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.VaccinesService;
import com.vetapp.veterinary.entity.Vacciness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacciness")
public class VaccinessController {

    @Autowired
    private VaccinesService vaccinesService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Vacciness> findAll(){
        return this.vaccinesService.findAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vacciness finByID(@PathVariable("id") long id){
        return this.vaccinesService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Vacciness save(@RequestBody Vacciness vacciness){
        return this.vaccinesService.save(vacciness);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Vacciness update(@RequestBody Vacciness vacciness){
        return vaccinesService.save(vacciness);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        return this.vaccinesService.delete((long) id);
    }
}



