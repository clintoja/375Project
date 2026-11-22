package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class admin_user {
    private int admin_user_id; 
    private String user_name;

    admin_user() {}

    admin_user(int admin_user_id, String user_name) {
        this.admin_user_id = admin_user_id;
        this.user_name = user_name;
    }

    public int GetAdminUserId() { return admin_user_id; }
    public String GetUserName() { return user_name; }
}
