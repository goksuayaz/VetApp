package com.vetapp.veterinary.business.concretes;


import com.vetapp.veterinary.business.abs.IAnimalService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {

    private AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepository.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepository.save(animal);
    }

    @Override
    public boolean delete(long id) {
        Animal animal = this.get(id);
        this.animalRepository.delete(animal);
        return true;
    }

    @Override
    public List<Animal> getAnimalsByName(String name) {
        return this.animalRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<Animal> getAnimalsByCustomerId(Long customerId) {
        return this.animalRepository.findByCustomerId(customerId);
    }

}
