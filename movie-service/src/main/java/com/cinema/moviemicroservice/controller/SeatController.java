package com.cinema.moviemicroservice.controller;

import com.cinema.moviemicroservice.model.Seat;
import com.cinema.moviemicroservice.model.Theater;
import com.cinema.moviemicroservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;
    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id){
        return new ResponseEntity<>(seatService.getSeatById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getSeats(){
        return new ResponseEntity<>(seatService.getAllSeats(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seat> postSeat(@Valid @RequestBody Seat seat){
        return new ResponseEntity<>(seatService.saveSeat(seat), HttpStatus.OK);
    }

    @PutMapping("/{seatId}/theater/{theaterId}")
    public ResponseEntity<Seat> addSeatToTheater(@PathVariable Long seatId, @PathVariable Long theaterId){
        return new ResponseEntity<>(seatService.addSeatToTheater(seatId, theaterId), HttpStatus.OK);
    }

    @GetMapping("/{seatId}/theater")
    public ResponseEntity<Theater> getEnrolledTheater(@PathVariable Long seatId){
        return new ResponseEntity<>(seatService.getEnrolledTheater(seatId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
