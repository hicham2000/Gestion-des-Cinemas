package com.example.gestiondecinema.service;

import com.example.gestiondecinema.dao.*;
import com.example.gestiondecinema.entities.Cinema;
import com.example.gestiondecinema.entities.Place;
import com.example.gestiondecinema.entities.Salle;
import com.example.gestiondecinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CinemaInitServiceImpl implements ICinemaIntService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Marakech", "Rabat", "Tangeer").forEach(v -> {
            Ville ville = new Ville();
            ville.setName(v);
            villeRepository.save(ville);

        });

    }

    @Override
    public void initCinemas() {

        villeRepository.findAll().forEach(v -> {
            Stream.of("Megarama","IMax","fonoun","chahrazade","daouiliz").forEach(cinemaname ->{
                Cinema cinema = new Cinema();
                cinema.setName(cinemaname);
                cinema.setVille(v);
                cinema.setNombresSalles(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i=0;i<cinema.getNombresSalles();i++){
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(20 + (int)(Math.random()*10));
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0;i<salle.getNombrePlace();i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void initSeances() {


    }

    @Override
    public void initCategories() {

    }

    @Override
    public void initFilms() {

    }

    @Override
    public void initProjection() {

    }

    @Override
    public void initTickets() {

    }
}
