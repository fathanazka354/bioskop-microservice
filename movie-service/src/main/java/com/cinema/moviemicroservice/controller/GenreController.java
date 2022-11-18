package com.cinema.moviemicroservice.controller;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreService genreService;
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getGenreById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getGenres(){
        return new ResponseEntity<>(genreService.getAllGenre(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> postGenre(@Valid @RequestBody Genre genre){
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.OK);
    }

    @PutMapping("/{genreId}/movie/{movieId}")
    public ResponseEntity<Genre> putGenreToMovie(@PathVariable Long genreId,@PathVariable Long movieId){
        return new ResponseEntity<>(genreService.addGenreToMovie(movieId, genreId), HttpStatus.OK);
    }

    @GetMapping("/{genreId}/movies")
    public ResponseEntity<Set<Movie>> getGenreToMovie(@PathVariable Long genreId){
        return new ResponseEntity<>(genreService.getEnrolledMovies(genreId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
