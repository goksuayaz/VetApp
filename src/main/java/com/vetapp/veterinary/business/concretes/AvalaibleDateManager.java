package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AvailableDateService;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.repository.AvailableDateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvalaibleDateManager implements AvailableDateService {


    @Override
    public AvailableDate getById(Long id) {
        return null;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        return null;
    }

    @Override
    public List<AvailableDate> findAll() {
        return null;
    }
}
