package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.IAvailableDateService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Appointment;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.repository.AppointmentRepository;
import com.vetapp.veterinary.repository.AvailableDateRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }
}
