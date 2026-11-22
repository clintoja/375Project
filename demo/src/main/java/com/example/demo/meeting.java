package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class meeting {
    private int meeting_id;
    private int admin_user_id;
    private String meeting_name;
    private String meeting_description;
    

    meeting(){};
    meeting (int meeting_id, int admin_user_id, String meeting_name, String meeting_description){


       this.meeting_id = meeting_id;
       this.admin_user_id = admin_user_id;
       this.meeting_name = meeting_name;
       this.meeting_description = meeting_description;

       
    }

    int GetMeetingId() { return meeting_id; }
    int GetAdminUserId() { return admin_user_id; }
    String GetMeetingName() { return meeting_name; }
    String GetMeetingDescription() { return meeting_description; }
}
