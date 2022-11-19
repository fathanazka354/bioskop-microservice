package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.MovieApplication;
import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import com.cinema.moviemicroservice.repository.MovieRepository;
import com.cinema.moviemicroservice.service.GenreService;
import com.cinema.moviemicroservice.service.GenreServiceImpl;
import com.cinema.moviemicroservice.service.MovieService;
import com.cinema.moviemicroservice.service.MovieServiceImpl;
import com.cinema.moviemicroservice.utils.MockDataMovie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MovieApplication.class)
@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Mock
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;
    private AutoCloseable autoCloseable;
    private MockDataMovie mockDataMovie;

    Movie movie = new Movie();
    Genre genre = new Genre();
    List<Movie> movieList = new ArrayList<>();
    List<Genre> genreList = new ArrayList<>();


    @BeforeEach
    void setUp() throws ParseException {
        autoCloseable = MockitoAnnotations.openMocks(this);
        movieServiceImpl = new MovieServiceImpl(movieRepository);
        mockDataMovie = new MockDataMovie();
        movie = mockDataMovie.movieDataFaker(movie);
        mockDataMovie.genreDataFaker(genre);

        movieList.add(movie);
        genreList.add(genre);
    }

    @AfterEach
    void tearUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    @DisplayName("When All Data Movie is Successfull")
    void whenAllData_thenReturnMovie() {
        movieServiceImpl.getAllMovie();
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("When All Data Movie is Failed")
    void whenAllData_thenReturnFalse() {
//        given
//        when
//        then
        movieServiceImpl.getAllMovie();
        verify(movieRepository).findAll();
        List<Movie> allMovie = movieServiceImpl.getAllMovie();
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> allMovie.get(1));
        System.out.println(throwable.getMessage());
        assertNotEquals("Index: 0, Size: 0",throwable.getMessage());
    }

    @Test
    @DisplayName("When Get Data By Id Movie is Successfully")
    void whenGetById_thenReturnTrue() throws ParseException {
        mockDataMovie.movieDataFaker(movie);
        when(movieServiceImpl.saveMovie(movie)).thenReturn(movie);
//        Movie movie1 = movieServiceImpl.getMovieById(movie);
//        System.out.println(movie1.getMovieId());

//        "The data using id '1' does not exists";
//        ArgumentCaptor<Movie> movieArgumentCaptor =
//                ArgumentCaptor.forClass(Movie.class);
//        System.out.println(movieArgumentCaptor.getValue());
//        verify(movieRepository).save(movie);
//        when(movieService.saveMovie(movie)).thenReturn(movie);
    }
//    @Mock
//    private MovieRepository movieRepository;
//
//    @Mock
//    private GenreRepository genreRepository;
//
//    @InjectMocks
//    private MovieServiceImpl movieServiceImpl;
//
//    @InjectMocks
//    private GenreServiceImpl genreServiceImpl;
//
//    @Mock
//    private MovieService movieService;
//
//    @Mock
//    private GenreService genreService;
//
//
//    Movie movie = new Movie();
//    Genre genre = new Genre();
//    List<Movie> movieList = new ArrayList<>();
//    List<Genre> genreList = new ArrayList<>();
//
//
//    @BeforeEach
//    void setUp() throws ParseException {
//        MockDataMovie mockDataMovie = new MockDataMovie();
//        mockDataMovie.movieDataFaker(movie);
//        mockDataMovie.genreDataFaker(genre);
//
//        movieList.add(movie);
//        genreList.add(genre);
//    }
//
//
//    @Test
//    @DisplayName("When All Data Movie is Successfull")
//    void whenAllData_thenReturnMovie() {
//        when(movieServiceImpl.getAllMovie()).thenReturn(movieList);
//        System.out.println("service = "+movieServiceImpl.getAllMovie().size());
//        System.out.println("customerList = "+movieList.size());
//        assertEquals(movieList, movieServiceImpl.getAllMovie());
//    }
//
//    @Test
//    @DisplayName("When All Data Movie is Failed")
//    void whenAllData_thenReturnFalseMovie() {
//        List<Movie> movieList1 = movieServiceImpl.getAllMovie();
//        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> movieList1.get(1));
//        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
//    }
//
//    @Test
//    @DisplayName("When All Data Genre is Successfull")
//    void whenAllData_thenReturnGenre() {
//        when(genreServiceImpl.getAllGenre()).thenReturn(genreList);
//        System.out.println("service = "+genreServiceImpl.getAllGenre().size());
//        System.out.println("customerList = "+genreList.size());
//        assertEquals(genreList, genreServiceImpl.getAllGenre());
//    }
//
//    @Test
//    @DisplayName("When All Data Genre is Failed")
//    void whenAllData_thenReturnFalseGenre() {
//        List<Genre> genreList1 = genreServiceImpl.getAllGenre();
//        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> genreList1.get(1));
//        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
//    }
//
//    @Test
//    @DisplayName("When Get Data Customer By id is Successfully")
//    void whenGetDataById_thenReturnGenre(){
//        final Long id = 1L;
//        when(genreServiceImpl.getAllGenre().get(0).getGenreId()).thenReturn(genre.getGenreId());
//        System.out.println("idService = "+genreServiceImpl.getGenreById(id).getGenreId());
//        System.out.println("idGenreMock = "+genre.getGenreId());
//    }

}
