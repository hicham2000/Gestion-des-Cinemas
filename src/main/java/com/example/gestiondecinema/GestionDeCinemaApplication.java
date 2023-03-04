package com.example.gestiondecinema;

import com.example.gestiondecinema.service.ICinemaIntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionDeCinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaIntService iCinemaIntService;

    public static void main(String[] args) {
        SpringApplication.run(GestionDeCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    iCinemaIntService.initVilles();
    iCinemaIntService.initCinemas();
    iCinemaIntService.initSalles();
    iCinemaIntService.initPlaces();
    iCinemaIntService.initSeances();
    iCinemaIntService.initCategories();
    iCinemaIntService.initFilms();
    iCinemaIntService.initProjection();
    iCinemaIntService.initTickets();
    }
}
