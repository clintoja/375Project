package com.example.demo;
import java.util.ArrayList;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.cglib.core.Local;

public class site_context {
    

    site_context(){
        CreateDB();

    }



    private void CreateDB(){
        try {
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );


            


            Statement create_tables_state = connection.createStatement();

            create_tables_state.execute("create table if not exists booking (booking_id INT PRIMARY KEY, scheduler_id INT REFERENCES scheduler(scheduler_id), start_time DATETIME, end_time DATETIME); \r\n" + //
                                "create table if not exists scheduler (scheduler_id INT PRIMARY KEY, start_date DATETIME, end_date DATETIME, minutes INT, meeting_id INT REFERENCES meeting(meeting_id));\r\n" + //
                                "create table if not exists admin_user(admin_user_id INT PRIMARY KEY, password VARCHAR(60), user_name VARCHAR(60));\r\n" + //
                                "create table if not exists meeting (meeting_id INT PRIMARY KEY, meeting_name VARCHAR(300), meeting_description VARCHAR(1000), admin_user_id INT REFERENCES admin_user(admin_user_id) );\r\n" + //
                                "create table if not exists user (user_id INT PRIMARY KEY, user_name VARCHAR(32), meeting_id INT REFERENCES meeting(meeting_id));\r\n" + //
                                "create table if not exists availability ( availability_id INT PRIMARY KEY, begin_time DATETIME, end_time DATETIME , week_day VARCHAR(32), user_id INT REFERENCES user(user_id), repeating BIT ); \r\n"
                                );

            


        } catch (SQLException s){

            s.printStackTrace();
        }
        AddSampleData();



    }

    public void AddSampleData(){

        try {
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );


            


            Statement create_tables_state = connection.createStatement();

            create_tables_state.execute("insert into admin_user VALUES(1, 'admin', 'admin' ); " + "\r\n" + 
                "insert into meeting VALUES(1, 'my meeting', 'wdawdawdawd', 1); " + "\r\n" + 
                "insert into scheduler VALUES(1, '2026-09-01 00:00', '2026-09-08 00:00', 60, 1); " + "\r\n" + 
                "insert into user VALUES(1, 1, 'james'); " + "\r\n" + 
                "insert into user VALUES(2, 1, 'james2'); " + "\r\n" + 
                "insert into user VALUES(3, 1, 'james3'); " + "\r\n" + 
                "insert into availability VALUES(1, '2026-09-01 00:00', '2026-09-01 02:00', ); " + "\r\n" +
                "insert into availability VALUES(1, '2026-09-02 00:00', '2026-09-01 02:00', ); " + "\r\n" +
                "insert into availability VALUES(2, '2026-09-01 00:00', '2026-09-01 02:00', ); " + "\r\n" +
                "insert into availability VALUES(2, '2026-09-02 00:00', '2026-09-01 02:00', ); " + "\r\n" +
                "insert into availability VALUES(3, '2026-09-01 00:00', '2026-09-01 02:00', ); " + "\r\n" +
                "insert into availability VALUES(3, '2026-09-02 00:00', '2026-09-01 02:00', ); " + "\r\n" 

            );
                                

            


        } catch (SQLException s){

            s.printStackTrace();
        }

    }
    public  ArrayList<user> GetUsersFromMeetingId(int meeting_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet users_from_meetingid;
            users_from_meetingid = user_model_statement.executeQuery("select * from user where meeting_id = " + meeting_id + ";");
            ArrayList<user> tmp = new  ArrayList<>();
            while (users_from_meetingid.next()){
                user u = new user(users_from_meetingid.getInt("user_id"),users_from_meetingid.getInt("meeting_id"), users_from_meetingid.getString("user_name") );

                tmp.add(u);


            }
            return tmp;

        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }

    public  ArrayList<availability> GetAvailabilityFromUserId(int user_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet availability_from_userid;
            availability_from_userid = user_model_statement.executeQuery("select * from availability where user_id = " + user_id + ";");
            ArrayList<availability> tmp = new  ArrayList<>();
            while (availability_from_userid.next()){




                LocalDateTime start,end;
                DateTimeFormatter dtf;
                dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
                start = LocalDateTime.parse(availability_from_userid.getString("start_time"),dtf);
                end = LocalDateTime.parse(availability_from_userid.getString("end_time"),dtf);
                availability a = new availability(availability_from_userid.getInt("availability_id"), availability_from_userid.getInt("user_id"),start,end, availability_from_userid.getString("week_day"), availability_from_userid.getInt("repeating") );

                tmp.add(a);


            }
            return tmp;

        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }




    public meeting GetMeeting(int meeting_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet meeting_from_meeting_id;
            meeting_from_meeting_id = user_model_statement.executeQuery("select * from meeting where meeting_id = " + meeting_id + ";");



            meeting tmp = new meeting(meeting_from_meeting_id.getInt("meeting_id"), meeting_from_meeting_id.getInt("admin_user_id"), meeting_from_meeting_id.getString("meeting_name"), meeting_from_meeting_id.getString("meeting_description"));
 

            


            return tmp;

        }catch (SQLException s){
            s.printStackTrace();
            return new meeting();
        }

    }


    //fill this in


    public int ValidateAdminUser( String user_name, String pass_word)
    {
        return 1;
       
    }

    
    public admin_user GetAdminUserFromMeetingId(int meet_id){





        return null;
    }
    public int CreateAdminUser(String user_name, String pass_word){






        return 1;
    }

    public int CreateBooking(booking book){




        return 1;
    }

    public int CreateUser(user u){


        return 1;
    }


    public int CreateMeeting(meeting meet){



        return 1;
    }

    public int CreateScheduler(int meeting_id){



        return 1;
    }

    public int CreateAvailability(availability av){



        return 1;
    }


    public int GetNewMeetingId(){
        return 0;
    }
}
