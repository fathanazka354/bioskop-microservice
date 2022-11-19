package com.cinema.moviemicroservice.repository;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.utils.MockDataMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    Movie movie = new Movie();
    Genre genre = new Genre();
    List<Movie> movieList = new ArrayList<>();
    List<Genre> genreList = new ArrayList<>();


    @BeforeEach
    void setUp() throws ParseException {
        MockDataMovie mockDataMovie = new MockDataMovie();
        mockDataMovie.movieDataFaker(movie);
        mockDataMovie.genreDataFaker(genre);

        movieList.add(movie);
        genreList.add(genre);
        movieRepository.save(movie);
    }

    @Test
    @DisplayName("When All Data Movie is Successfull")
    void whenAllData_thenReturnTrue() {
//        when(movieRepository.findAll()).thenReturn(movieList);
        System.out.println("service = "+ movieRepository.findAll().get(0).getMovieId());
        System.out.println("customerList = "+ movieList.get(0).getMovieId());
//        assertEquals(movieList, movieRepository.findAll());
        assertEquals(movieList.get(0).getMovieId(), movieRepository.findAll().get(0).getMovieId());
    }

    @Test
    @DisplayName("When All Data Movie is Failed")
    void whenAllData_thenReturnFalse() {
//        when(movieRepository.findAll()).thenReturn(movieList);
//        assertEquals(movieList, movieRepository.findAll());
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> movieRepository.findAll().get(1));
        System.out.println(throwable.getMessage());
        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
    }

    @Test
    @DisplayName("When Get Data Movie By id is Successfully")
    void whenGetDataById_thenReturnTrue(){
        final Long id = 1L;

//        when(genreServiceImpl.getAllGenre().get(0).getGenreId()).thenReturn(genre.getGenreId());
        System.out.println("idService = " + movieRepository.findById(id).get().getMovieId());
        assertEquals(id, movieRepository.findById(id).get().getMovieId());
//        System.out.println("idGenreMock = "+genre.getGenreId());
    }

    @Test
    @DisplayName("When Get Data Movie By id is Failed")
    void whenGetDataById_thenReturnFalse(){
        final Long id = 0L;
        assertTrue(movieRepository.findById(id).isEmpty());
    }

}
