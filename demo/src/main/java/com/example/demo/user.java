package com.example.demo;


import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class user {


    user (int user, int meeting_id ){

        
        this.user_id = user;
        this.meeting_id = meeting_id;
    }
    private int user_id;
    private int meeting_id;
    user() {}

    int GetUserId(){ return user_id; }
   
}
