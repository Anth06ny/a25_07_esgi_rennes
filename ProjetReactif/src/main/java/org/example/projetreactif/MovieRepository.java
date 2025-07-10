package org.example.projetreactif;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveCrudRepository<Movie, Long> {
    // Méthode personnalisée pour rechercher par titre
    Flux<Movie> findByTitle(String title);
}

