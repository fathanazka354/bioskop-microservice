package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GenreService {
    Genre getGenreById(Long id);
    Genre saveGenre(Genre genre);
    List<Genre> getAllGenre();
    void deleteGenre(Long id);
    Genre addGenreToMovie(Long movieId, Long genreId);
    Set<Movie> getEnrolledMovies(Long id);
}
