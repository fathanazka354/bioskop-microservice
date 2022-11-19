package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.dto.TaskDefinition;
import com.cinema.moviemicroservice.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TaskDefinitionBean implements Runnable{
    private TaskDefinition taskDefinition;

    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Override
    public void run() {
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate getNow = LocalDate.now();
        System.out.println("Reminder: \nMovie that have title is " +
                showTimeRepository.findFilmThatShowing(LocalDate.parse(formatTanggal.format(getNow))).get(0).getMovie().getMovieName() +
                " will Launching today in date " +
                showTimeRepository.findFilmThatShowing(LocalDate.parse(formatTanggal.format(getNow))).get(0).getDateShowtime() +
                " or perfectly at " + taskDefinition.getDate());
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}
