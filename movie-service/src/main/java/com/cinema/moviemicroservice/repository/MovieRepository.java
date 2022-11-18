package com.cinema.moviemicroservice.repository;

import com.cinema.moviemicroservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
