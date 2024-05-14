package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.AvailableDate;

import java.util.List;

public interface AvailableDateService {

    AvailableDate getById(Long id);

    AvailableDate save(AvailableDate availableDate);

    String delete(Long id);

    AvailableDate update(AvailableDate availableDate);

    List<AvailableDate> findAll();

}
