package com.cinema.moviemicroservice.controller;

import com.cinema.moviemicroservice.model.Theater;
import com.cinema.moviemicroservice.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id){
        return new ResponseEntity<>(theaterService.getTheaterById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getTheaters(){
        return new ResponseEntity<>(theaterService.getAllTheater(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Theater> postTheater(@Valid @RequestBody Theater theater){
        return new ResponseEntity<>(theaterService.saveTheater(theater), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
