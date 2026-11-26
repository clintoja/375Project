package com.example.demo;


import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class user {


    user (int user, int meeting_id, String user_name ){

        
        this.user_id = user;
        this.meeting_id = meeting_id;
        this.user_name = user_name;
    }
    private int user_id;
    private String user_name;
    private int meeting_id;
    user() {}

    public int GetUserId(){ return user_id; }
    public String GetUserName() {return user_name; }
    public int GetMeetingId() { return meeting_id; }
}
