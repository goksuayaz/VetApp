package com.vetapp.veterinary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "availableDate")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AvailableDate {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "avalaibleDate")
    private LocalDate availableDate;


}
