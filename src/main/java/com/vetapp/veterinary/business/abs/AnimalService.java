package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Animal;

import java.util.List;

public interface AnimalService {

    Animal getById(Long id);
    Animal save(Animal animal);
    Animal update(Animal animal);
    String delete(Long id);
    List<Animal> findAll();



}
