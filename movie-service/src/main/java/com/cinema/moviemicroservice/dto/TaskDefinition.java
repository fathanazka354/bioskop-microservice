package com.cinema.moviemicroservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDefinition {
    private String cronExpression;
    private String actionType;
    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private Date date;
    private String data;
}