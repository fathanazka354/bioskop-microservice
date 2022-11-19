package com.cinema.moviemicroservice.repository;

import com.cinema.moviemicroservice.MovieApplication;
import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.service.GenreService;
import com.cinema.moviemicroservice.service.GenreServiceImpl;
import com.cinema.moviemicroservice.service.MovieService;
import com.cinema.moviemicroservice.service.MovieServiceImpl;
import com.cinema.moviemicroservice.utils.MockDataMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @InjectMocks
    private GenreServiceImpl genreServiceImpl;

    @Mock
    private MovieService movieService;

    @Mock
    private GenreService genreService;


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
    }


    @Test
    @DisplayName("When All Data Movie is Successfull")
    void whenAllData_thenReturnMovie() {
        when(movieServiceImpl.getAllMovie()).thenReturn(movieList);
        System.out.println("service = "+movieServiceImpl.getAllMovie().size());
        System.out.println("customerList = "+movieList.size());
        assertEquals(movieList, movieServiceImpl.getAllMovie());
    }

    @Test
    @DisplayName("When All Data Movie is Failed")
    void whenAllData_thenReturnFalseMovie() {
        List<Movie> movieList1 = movieServiceImpl.getAllMovie();
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> movieList1.get(1));
        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
    }

    @Test
    @DisplayName("When All Data Genre is Successfull")
    void whenAllData_thenReturnGenre() {
        when(genreServiceImpl.getAllGenre()).thenReturn(genreList);
        System.out.println("service = "+genreServiceImpl.getAllGenre().size());
        System.out.println("customerList = "+genreList.size());
        assertEquals(genreList, genreServiceImpl.getAllGenre());
    }

    @Test
    @DisplayName("When All Data Genre is Failed")
    void whenAllData_thenReturnFalseGenre() {
        List<Genre> genreList1 = genreServiceImpl.getAllGenre();
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> genreList1.get(1));
        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
    }

    @Test
    @DisplayName("When Get Data Customer By id is Successfully")
    void whenGetDataById_thenReturnGenre(){
        final Long id = 1L;
        when(genreServiceImpl.getAllGenre().get(0).getGenreId()).thenReturn(genre.getGenreId());
        System.out.println("idService = "+genreServiceImpl.getGenreById(id).getGenreId());
        System.out.println("idGenreMock = "+genre.getGenreId());
    }
}
