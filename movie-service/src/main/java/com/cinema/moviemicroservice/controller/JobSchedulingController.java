package com.cinema.moviemicroservice.controller;

import com.cinema.moviemicroservice.dto.TaskDefinition;
import com.cinema.moviemicroservice.dto.TaskDefinitionBean;
import com.cinema.moviemicroservice.service.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.UUID;
@RestController
@RequestMapping(path = "/schedule")
public class JobSchedulingController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        taskDefinitionBean.setTaskDefinition(taskDefinition);

        taskSchedulingService.scheduleATask(UUID.randomUUID().toString(), taskDefinitionBean, taskDefinition.getCronExpression());
    }

    @GetMapping(path="/remove/{jobid}")
    public void removeJob(@PathVariable String jobid) {
        taskSchedulingService.removeScheduledTask(jobid);
    }
}
