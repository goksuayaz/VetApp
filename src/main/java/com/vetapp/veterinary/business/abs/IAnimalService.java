package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);
    Animal get(Long id);
    Page<Animal> cursor(int page, int pageSize);
    Animal update (Animal animal);
    boolean delete (long id);
    List<Animal> getAnimalsByName(String name);
    List<Animal> getAnimalsByCustomerId(Long customerId);

}

