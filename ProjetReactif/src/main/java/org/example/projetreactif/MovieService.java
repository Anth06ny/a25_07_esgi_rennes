package org.example.projetreactif;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Récupérer toutes les films
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}