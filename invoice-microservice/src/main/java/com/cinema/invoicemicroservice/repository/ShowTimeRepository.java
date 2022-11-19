package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    @Transactional
    @Modifying
    @Query("SELECT s FROM ShowTime s WHERE s.dateShowtime = :current")
    public List<ShowTime> findFilmThatShowing(@Param("current") LocalDate current);

    @Query("SELECT s FROM ShowTime s WHERE s.dateShowtime = :date")
    public ShowTime findFilmByDate(@Param("date")LocalDate date);
}
