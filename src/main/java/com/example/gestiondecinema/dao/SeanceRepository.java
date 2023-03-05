package com.example.gestiondecinema.dao;

import com.example.gestiondecinema.entities.Salle;
import com.example.gestiondecinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SeanceRepository extends JpaRepository<Seance,Long> {
}
