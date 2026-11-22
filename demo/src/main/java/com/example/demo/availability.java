package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class availability {
    private int availability_id;
    private int user_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String weekDay;
    private int repeating; 
    private TimeZone timeZone;
    

    availability(int availability_id, int user_id, LocalDateTime startTime, LocalDateTime endTime, String weekDay, int repeating){

        this.availability_id = availability_id;
        this.user_id = user_id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekDay  = weekDay;
        this.repeating = repeating;


    }
}
