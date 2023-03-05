package com.example.gestiondecinema.service;

import com.example.gestiondecinema.dao.*;
import com.example.gestiondecinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public void initCategories() {
        Stream.of("Histoir","action","fiction","drama").forEach(c->{
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categorieRepository.save(categorie);
        });

    }

    @Override
    public void initFilms() {
        double[] durrees = new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories = categorieRepository.findAll();
    Stream.of("the last of us","spider man","iron man").forEach(f->{
        Film film = new Film();
        film.setTitre(f);
        film.setDuree(durrees[new Random().nextInt(durrees.length)]);
        film.setPhoto(f.replaceAll(" ", ""));
        film.setCategorie(categories.get(new Random().nextInt(categories.size())));
        filmeRepository.save(film);
    });
    }

    @Override
    public void initProjection() {
        double[] prix = new double[]{30,50,100,60,70,90};
        villeRepository.findAll().forEach(v -> {
            v.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmeRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prix[new Random().nextInt(prix.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(projection.getPrix());
                ticket.setProjection(projection);
                ticket.setReservee(false);
                ticketRepository.save(ticket);
            });
        });

    }
}
