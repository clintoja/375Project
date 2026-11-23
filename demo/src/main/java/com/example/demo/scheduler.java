package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class scheduler {
    private int scheduler_id; 
    private LocalDateTime startTime;
        private LocalDateTime endTime;
    private int meeting_id;
private int minutes;


    scheduler() {}

    scheduler(int scheduler_id, LocalDateTime startTime, LocalDateTime endTime, int minutes, int meet_id ){

        this.scheduler_id = scheduler_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meeting_id = meet_id;
        this.minutes = minutes;
    }
    public LocalDateTime GetStartTime() {
        return startTime;
    }
    public int getSchedulerId() {
        return scheduler_id;
    }
}
