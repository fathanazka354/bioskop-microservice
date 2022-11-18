package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.exception.DataNotFoundException;
import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository movieRepository;
    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> entity = movieRepository.findById(id);
        return unwrapMovie(entity, id);
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Set<Genre> getEnrolledGenres(Long id) {
        Movie movie = getMovieById(id);
        return movie.getGenres();
    }

    static Movie unwrapMovie(Optional<Movie> entity, Long id){
        if (entity.isPresent()) return entity.get();
        throw new DataNotFoundException(id);
    }
}
