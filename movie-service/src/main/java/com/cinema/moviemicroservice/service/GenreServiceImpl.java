package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.exception.DataNotFoundException;
import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.repository.GenreRepository;
import com.cinema.moviemicroservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    MovieRepository movieRepository;
    @Override
    public Genre getGenreById(Long id) {
        Optional<Genre> entity = genreRepository.findById(id);
        return unwrapGenre(entity, id);
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Genre addGenreToMovie(Long movieId, Long genreId) {
        Genre genre = getGenreById(genreId);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()){
            Movie movie1 = movie.get();
            genre.getMovies().add(movie1);
            return genreRepository.save(genre);
        }
        return null;
    }

    @Override
    public Set<Movie> getEnrolledMovies(Long id) {
        Genre genre = getGenreById(id);
        return genre.getMovies();
    }

    static Genre unwrapGenre(Optional<Genre> entity, Long id){
        if (entity.isPresent()) return entity.get();
        throw new DataNotFoundException(id);
    }
}
