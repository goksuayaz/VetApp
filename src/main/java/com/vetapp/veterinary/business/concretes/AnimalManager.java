package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AnimalService;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimalManager implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Animal getById(Long id) {
        if (this.animalRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id yok");
        } else {
            return this.animalRepository.findById(id);
        }

    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepository.save(animal);
    }

    @Override
    public String delete(Long id) {
        if (this.animalRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            this.animalRepository.delete(this.getById(id));
            return "deleted the record with id: " + id;
        }

    }

    @Override
    public Animal update(Animal animal) {
        Animal existingAnimal = animalRepository.findById(animal.getId());
        if (existingAnimal == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            existingAnimal.setBirthday(animal.getBirthday());
            existingAnimal.setColour(animal.getColour());
            existingAnimal.setBreed(animal.getBreed());
            existingAnimal.setGender(animal.getGender());
            existingAnimal.setName(animal.getName());
            existingAnimal.setSpecies(animal.getSpecies());
            return animalRepository.save(animal);
        }

    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }
}
