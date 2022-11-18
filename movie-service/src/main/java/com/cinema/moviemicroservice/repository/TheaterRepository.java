package com.cinema.moviemicroservice.repository;

import com.cinema.moviemicroservice.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
