package com.cinema.moviemicroservice.service;

import com.cinema.moviemicroservice.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private ShowTimeRepository showTimeRepository;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(String jobId, Runnable tasklet, String cronExpression) {
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate getNow = LocalDate.now();
        System.out.println("Reminder: \nMovie that have title is " + showTimeRepository.findFilmThatShowing(LocalDate.parse(formatTanggal.format(getNow))).get(0).getMovie().getMovieName() + " will Launching today in date " + showTimeRepository.findFilmThatShowing(LocalDate.parse(formatTanggal.format(getNow))).get(0).getDateShowtime());
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression,
                TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
