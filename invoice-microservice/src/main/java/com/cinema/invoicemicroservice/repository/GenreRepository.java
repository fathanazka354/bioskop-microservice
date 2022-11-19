package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
