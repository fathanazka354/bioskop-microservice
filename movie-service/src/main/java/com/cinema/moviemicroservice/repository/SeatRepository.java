package com.cinema.moviemicroservice.repository;

import com.cinema.moviemicroservice.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
