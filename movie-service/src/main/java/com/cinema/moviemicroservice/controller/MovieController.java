package com.cinema.moviemicroservice.controller;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/{movieId}/genres")
    public ResponseEntity<Set<Genre>> getEnrolledMovies(@PathVariable Long movieId){
        return new ResponseEntity<>(movieService.getEnrolledGenres(movieId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity<>(movieService.getAllMovie(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> postMovie(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
