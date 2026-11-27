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
    public int GetSchedulerId() {
        return scheduler_id;
    }
        public LocalDateTime GetEndTime() {
        return endTime;
    }

            public int GetMeetingId() {
        return meeting_id;
    }
              public int GetMinutes() {
        return minutes;
    }

        public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

        public void setEndTime(LocalDateTime endTime) {
        this.endTime =  endTime;
    }

            public void setMeetingId(int meetint_id) {
        this.meeting_id =  meeting_id;
    }
              public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
