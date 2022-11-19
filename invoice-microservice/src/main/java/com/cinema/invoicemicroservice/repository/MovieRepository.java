package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
