package org.example.projetreactif;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //http://localhost:8080/api/movies
    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
