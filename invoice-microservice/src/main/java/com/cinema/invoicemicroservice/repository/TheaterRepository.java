package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
