package com.example.gestiondecinema.dao;

import com.example.gestiondecinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmeRepository extends JpaRepository<Film,Long> {
}
