package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class meeting_data_repository {

    site_context sc;

    meeting_data_repository(){
         sc = new site_context();

    }





    ArrayList<user> GetUsersFromMeeting(int meeting_id){



        return  sc.GetUsersFromMeetingId(meeting_id);

    }



    meeting GetMeetingFromMeetId(int meet_id){


        return sc.GetMeeting(meet_id);
    }



    admin_user GetAdminUserFromMeetingId(int meet_id){



        return sc.GetAdminUserFromMeetingId(meet_id);
    }

    ArrayList<availability> GetAvailabilityFromUserId(int user_id){



        return sc.GetAvailabilityFromUserId(user_id);
    }


    //fill this out
    ArrayList<LocalDateTime> GetAvailableTimes(int meet_id){


        return  null;
    }

    ArrayList<booking> GetBookingFromSchedule(int scheduler_id){

        
        return sc.GetBookingFromSchedule(scheduler_id);
    }

    ArrayList<scheduler> GetSchedulerFromMeeting(int meeting_id){

        return  sc.GetSchedulerFromMeeting( meeting_id);
    }

    scheduler GetLatestScheduler(int meeting_id){
        return  sc.GetSchedulerFromMeeting( meeting_id).stream().max(Comparator.comparing(scheduler::GetStartTime)).orElse(null);
    }

    //create functions are limited and will need work depending on how we do front end
    int CreateBooking(){

        return sc.CreateBooking(new booking());

    }

    int CreateMeeting(String meeting_name, String meeting_description, int admin_user_id){


        return  sc.CreateMeeting(new meeting(sc.GetNewMeetingId(), admin_user_id, meeting_name, meeting_description ));

    }

    int CreateAdminUser(String user_name, String password){

        return  sc.CreateAdminUser(user_name,password );
    }



    int CreateScheduler(int meeting_id){

        return sc.CreateScheduler( meeting_id);
    }
    int CreateAvailability(availability availability){
        return sc.CreateAvailability(availability);
    }
    int CreateUser(){
        return sc.CreateUser(new user());

    }


    

}
