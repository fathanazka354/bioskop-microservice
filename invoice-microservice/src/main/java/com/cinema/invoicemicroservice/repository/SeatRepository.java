package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
