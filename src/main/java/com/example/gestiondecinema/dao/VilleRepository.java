package com.example.gestiondecinema.dao;

import com.example.gestiondecinema.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville,Long> {
}
