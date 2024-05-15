package com.vetapp.veterinary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "vaccines")
public class Vacciness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String vacciness_name;

    @Column(name = "code",unique = true)
    private String vacciness_code;

    @OneToMany(mappedBy = "vaccines",cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Vaccine> vaccineList;

}
