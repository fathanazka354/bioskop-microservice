package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.model.Seat;
import com.cinema.moviemicroservice.model.Theater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    Seat getSeatById(Long id);
    Seat saveSeat(Seat seat);
    List<Seat> getAllSeats();
    void deleteSeat(Long id);
    Seat addSeatToTheater(Long seatId, Long theaterId);
    Theater getEnrolledTheater(Long seatId);
}
