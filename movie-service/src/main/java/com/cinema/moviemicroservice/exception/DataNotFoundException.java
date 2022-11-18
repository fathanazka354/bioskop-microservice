package com.cinema.moviemicroservice.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(Long id){
        super("The data using id '"+ id + "' does not exists");
    }

    public DataNotFoundException() {

    }
}
