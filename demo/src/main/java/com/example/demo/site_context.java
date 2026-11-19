package com.example.demo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class site_context {
    

    site_context(){
        CreateDB();

    }



    private void CreateDB(){
        try {
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );


            


            Statement create_tables_state = connection.createStatement();

            create_tables_state.execute("create table booking (booking_id INT PRIMARY KEY, scheduler_id INT REFERENCES scheduler(scheduler_id), start_time DATETIME, end_time DATETIME); \r\n" + //
                                "create table scheduler (scheduler_id INT PRIMARY KEY, start_date DATETIME, end_date DATETIME, meeting_id INT REFERENCES meeting(meeting_id));\r\n" + //
                                "create table admin_user(admin_user_id INT PRIMARY KEY, password VARCHAR(60), user_name VARCHAR(60));\r\n" + //
                                "create table meeting (meeting_id INT PRIMARY KEY, admin_user_id INT REFERENCES admin_user(admin_user_id) );\r\n" + //
                                "create table user (user_id CHAR(36) PRIMARY KEY,  meeting_id INT REFERENCES meeting(meeting_id));\r\n" + //
                                "create table availability ( availability_id INT PRIMARY KEY, begin_time TIME, end_time TIME , week_day VARCHAR(32), repeating BIT ); \r\n"
                                );

            


        } catch (SQLException s){

            s.printStackTrace();
        }



    }



}
