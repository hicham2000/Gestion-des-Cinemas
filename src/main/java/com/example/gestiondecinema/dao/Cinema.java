package com.example.gestiondecinema.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collections;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    private int nombresSalles;
    @OneToMany(mappedBy = "cinema")
    private Collections<Salle> salles;
    @ManyToOne
    private Ville ville;


}
