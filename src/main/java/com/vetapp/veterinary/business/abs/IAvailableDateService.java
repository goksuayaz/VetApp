package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate update (AvailableDate availableDate);
    boolean delete (long id);





}