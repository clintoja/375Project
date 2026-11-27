package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SiteContextTests {
    @Test
    @Disabled("Test pending")
    void addSampleData() {
    }

    @Test
    
    void getUsersFromMeetingId() {
        site_context sc = new site_context();

        //sc.AddSampleData();

        assertEquals(sc.GetUsersFromMeetingId(1).get(0).GetUserName(), "james" );



    }

    @Test
    
    void getAvailabilityFromUserId() {

                site_context sc = new site_context();

        //sc.AddSampleData();

        assertEquals(sc.GetAvailabilityFromUserId(1).get(0).GetWeekDay(), "Tuesday" );


    }

    @Test
    
    void getBookingFromSchedule() {
                site_context sc = new site_context();

        sc.AddSampleData();

        assertEquals(sc.GetBookingFromSchedule(1).get(0).GetStartTime().getDayOfMonth(), 2 );

    }

    @Test
   
    void getSchedulerFromMeeting() {

                        site_context sc = new site_context();

        sc.AddSampleData();

        assertEquals(sc.GetSchedulerFromMeeting(1).get(0).GetStartTime().getDayOfMonth(), 1);
    }

    @Test
  
    void getMeeting() {


                        site_context sc = new site_context();

        sc.AddSampleData();

        assertEquals(sc.GetMeeting(1).GetMeetingName(),"my meeting"  );

    }

    @Test
    @Disabled("Test pending")
    void validateAdminUser() {
    }

    @Test

    void getAdminUserFromMeetingId() {


        

                        site_context sc = new site_context();

        sc.AddSampleData();

        assertEquals(sc.GetAdminUserFromMeetingId(1).GetUserName(),"admin"  );
    }

    @Test
    @Disabled("Test pending")
    void createAdminUser() {
    }

    @Test
    @Disabled("Test pending")
    void createBooking() {
    }

    @Test
    @Disabled("Test pending")
    void createUser() {
    }

    @Test
    @Disabled("Test pending")
    void createMeeting() {
    }

    @Test
    @Disabled("Test pending")
    void createScheduler() {
    }

    @Test
    @Disabled("Test pending")
    void createAvailability() {
    }

    @Test
    @Disabled("NonUsed Function")
    void getNewMeetingId() {
    }
}
