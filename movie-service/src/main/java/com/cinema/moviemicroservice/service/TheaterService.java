package com.cinema.moviemicroservice.service;


import com.cinema.moviemicroservice.model.Theater;

import java.util.List;

public interface TheaterService {
    Theater getTheaterById(Long id);
    Theater saveTheater(Theater theater);
    List<Theater> getAllTheater();
    void deleteTheater(Long id);
}
