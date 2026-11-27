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

    public int GetAvailabilityId() { return availability_id; }
    public int GetUserId() { return user_id; }
    public LocalDateTime GetStartTime() { return startTime; }
    public LocalDateTime GetEndTime() { return endTime; }
    public String GetWeekDay() { return weekDay; }
    public int GetRepeating() { return repeating; }




    public void setUserId(int user_id ) { this.user_id = user_id; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setWeekDay( String weekDay) { this.weekDay = weekDay; }
    public void setRepeating(int repeating) { this.repeating = repeating; }
}
