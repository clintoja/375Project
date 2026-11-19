package com.example.demo;

import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class availability {
    private int availability_id;
    private int user_id;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String weekDay;
    private int repeating; 
}
