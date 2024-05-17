package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.IVaccineService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.Vaccine;
import com.vetapp.veterinary.repository.AnimalRepository;
import com.vetapp.veterinary.repository.VaccineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepository vaccineRepository;

    private final AnimalRepository animalRepository;

    public VaccineManager(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }

    /*@Override
    public Vaccine save(Vaccine vaccine, Long animalId) {
        log.info("vaccine : {},animalId : {}",vaccine,animalId);
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        vaccine.setAnimal(animal);
        log.info("animal : {}",animal);
        return vaccineRepository.save(vaccine);
    }
    */


    @Override
    public Vaccine save(Vaccine vaccine, Long animalId) {
        return null;
    }


    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepository.findAll(pageable);
    }


    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepository.save(vaccine);
    }


    @Override
    public boolean delete(long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepository.delete(vaccine);
        return true;
    }


    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {
        return vaccineRepository.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> getVaccinesByDateRangeForAnimal(Long animalId, LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByAnimalIdAndProtectionStartDateBetween(animalId,startDate,endDate);
    }

    @Override
    public List<Vaccine> getVaccinesByDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByProtectionStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate,Msg.NOT_FOUND);
        Objects.requireNonNull(endDate, Msg.NOT_FOUND);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(Msg.HISTORY_CONTROLLER);
        }

        return vaccineRepository.findByProtectionStartDateBetween(startDate, endDate);

    }

    @Override
    public List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(animalId, Msg.NOT_FOUND);
        Objects.requireNonNull(startDate, Msg.NOT_FOUND);
        Objects.requireNonNull(endDate, Msg.NOT_FOUND);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(Msg.HISTORY_CONTROLLER);
        }
        return vaccineRepository.findByAnimalIdAndProtectionStartDateBetween(animalId, startDate, endDate);

    }


}

