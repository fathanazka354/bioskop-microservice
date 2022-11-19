package com.cinema.moviemicroservice.utils;

import com.cinema.moviemicroservice.model.Genre;
import com.cinema.moviemicroservice.model.Movie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MockDataMovie {
    public Movie movieDataMock(Movie movie, Movie data){
        movie.setMovieId(data.getMovieId());
        movie.setMovieCode(data.getMovieCode());
        movie.setMovieName(data.getMovieName());
        movie.setCategory(data.getCategory());
        movie.setSubTitle(data.getSubTitle());
        return movie;
    }

    public Genre genreDataMock(Genre genre, Genre data){
        genre.setGenreId(data.getGenreId());
        genre.setNameGenre(data.getNameGenre());
        genre.setCreatedAt(data.getCreatedAt());
        return genre;
    }

    public Movie movieDataFaker(Movie movie) throws ParseException {
        movie.setMovieId(1L);
        movie.setMovieCode("M0001");
        movie.setMovieName("Jon Wick");
        movie.setCategory("Family");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-12 20:20:20");
        movie.setSubTitle("Indonesia");
        movie.setCreatedAt(date);

        return movie;
    }

    public Genre genreDataFaker(Genre genre) throws ParseException {
        genre.setGenreId(1L);
        genre.setNameGenre("Romance");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-12 20:20:20");
        genre.setCreatedAt(date);

        return genre;
    }
}
