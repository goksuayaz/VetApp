package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AnimalService;
import com.vetapp.veterinary.entity.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements AnimalService {


    @Override
    public Animal getById(Long id) {
        return null;
    }

    @Override
    public Animal save(Animal animal) {
        return null;
    }

    @Override
    public Animal update(Animal animal) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<Animal> findAll() {
        return null;
    }

    @Override
    public List<Animal> findByCustomerId(Long customer_id) {
        return null;
    }

    @Override
    public List<Animal> findByAnimalId(Long animal_id) {
        return null;
    }

    @Override
    public Animal getByName(String name) {
        return null;
    }
}
