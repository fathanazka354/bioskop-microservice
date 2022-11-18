package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.exception.DataNotFoundException;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.model.Seat;
import com.cinema.moviemicroservice.model.ShowTime;
import com.cinema.moviemicroservice.repository.MovieRepository;
import com.cinema.moviemicroservice.repository.SeatRepository;
import com.cinema.moviemicroservice.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.cinema.moviemicroservice.service.MovieServiceImpl.unwrapMovie;
import static com.cinema.moviemicroservice.service.SeatServiceImpl.unwrapSeat;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{
    @Autowired
    ShowTimeRepository showTimeRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    MovieRepository movieRepository;
    @Override
    public ShowTime getShowTimeById(Long id) {
        Optional<ShowTime> entity = showTimeRepository.findById(id);
        return unwrapShowTime(entity, id);
    }

    @Override
    public ShowTime saveShowTime(ShowTime showTime) {
        return showTimeRepository.saveAndFlush(showTime);
    }

    @Override
    public List<ShowTime> getAllShowTime() {
        return showTimeRepository.findAll();
    }

    @Override
    public void deleteShowTime(Long id) {
        showTimeRepository.deleteById(id);
    }

    @Override
    public ShowTime addSeatToShowtime(Long seatId, Long showtimeId) {
        Optional<ShowTime> entity = showTimeRepository.findById(showtimeId);
        ShowTime showTime = unwrapShowTime(entity, showtimeId);
        Optional<Seat> entitySeat = seatRepository.findById(seatId);
        Seat seat = unwrapSeat(entitySeat, showtimeId);
        showTime.setSeat(seat);
        return showTimeRepository.saveAndFlush(showTime);
    }

    @Override
    public ShowTime addMovieToShowtime(Long movieId, Long showtimeId) {
        Optional<ShowTime> entity = showTimeRepository.findById(showtimeId);
        ShowTime showTime = unwrapShowTime(entity, showtimeId);
        Optional<Movie> entityMovie = movieRepository.findById(movieId);
        Movie  movie = unwrapMovie(entityMovie, showtimeId);
        showTime.setMovie(movie);
        return showTimeRepository.saveAndFlush(showTime);
    }

    @Override
    public Seat getEnrolledSeat(Long showtimeId) {
        Optional<ShowTime> entity = showTimeRepository.findById(showtimeId);
        ShowTime showTime = unwrapShowTime(entity, showtimeId);
        return showTime.getSeat();
    }

    @Override
    public Movie getEnrolledMovie(Long showtimeId) {
        Optional<ShowTime> entity = showTimeRepository.findById(showtimeId);
        ShowTime showTime = unwrapShowTime(entity, showtimeId);
        return showTime.getMovie();
    }

    @Override
    public List<ShowTime> getCurrentFilmShowing(LocalDate date) {
        return showTimeRepository.findFilmThatShowing(date);
    }

    @Override
    public ShowTime getFilmByDate(LocalDate date) {
        return showTimeRepository.findFilmByDate(date);
    }

    static ShowTime unwrapShowTime(Optional<ShowTime> entity, Long id){
        if (entity.isPresent()) return entity.get();
        throw new DataNotFoundException(id);
    }
}
