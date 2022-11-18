package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MovieService {
    Movie getMovieById(Long id);
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovie();
    void deleteMovie(Long id);
    Set<Genre> getEnrolledGenres(Long id);
}
