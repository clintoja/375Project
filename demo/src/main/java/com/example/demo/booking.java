package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
public class booking {
    
    private int booking_id;
    private int scheduler_id; 
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    booking() {}

    booking(int booking_id, int scheduler_id, LocalDateTime starTime, LocalDateTime endTime){

        this.booking_id = booking_id;
        this.scheduler_id = scheduler_id;
        this.startTime = starTime;
        this.endTime = endTime;
    }

    int GetBookingId() { return booking_id; }
    int GetSchedulerId() { return scheduler_id; }
    LocalDateTime GetStartTime() { return startTime; }
    LocalDateTime GetEndTime() { return endTime; }
}
