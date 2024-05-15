package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Vacciness;

import java.util.List;

public interface VaccinesService {

    Vacciness getById(long id);

    Vacciness save(Vacciness vacciness);

    String delete(long id);

    Vacciness update(Vacciness vacciness);

    List<Vacciness> findAll();
}



