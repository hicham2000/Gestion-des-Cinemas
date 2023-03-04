package com.example.gestiondecinema.entities;

import com.example.gestiondecinema.entities.Ville;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    private int nombresSalles;

    @OneToMany(mappedBy = "cinema")
    private List<Salle> salles;
    @ManyToOne
    private Ville ville;


}
