package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.AvailableDate;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);

}