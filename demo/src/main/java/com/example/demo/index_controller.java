package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller 
public class index_controller {
    


    
meeting_data_repository mdr = new meeting_data_repository();

@GetMapping("/create-meet")
public String create_meet(Model model) {
    return "create-meet";

    
}
@GetMapping("")
public String index(Model model) {
    return "index";

    
}

@GetMapping("view-meet")
public String view_meet(@RequestParam String meet_id, Model model) {
    meeting meet = mdr.GetMeetingFromMeetId(Integer.parseInt(meet_id));

    ArrayList<user> users = mdr.GetUsersFromMeeting(Integer.parseInt(meet_id));
    admin_user au = mdr.GetAdminUserFromMeetingId(Integer.parseInt(meet_id));
    System.out.print(meet.GetMeetingName());
    for (user u: users){
        model.addAttribute(mdr.GetAvailabilityFromUserId(u.GetUserId()));
    }
    //model.addAttribute(mdr.GetAvailableTimes(Integer.parseInt(meet_id)));
    model.addAttribute(au) ;
    model.addAttribute(meet);
    model.addAttribute(users);
    

    return "view-meet";

    
}

@GetMapping("edit-meet")
public String edit_meet(@RequestParam String meet_id, Model model) {
        meeting meet = mdr.GetMeetingFromMeetId(Integer.parseInt(meet_id));

    ArrayList<user> users = mdr.GetUsersFromMeeting(Integer.parseInt(meet_id));
    admin_user au = mdr.GetAdminUserFromMeetingId(Integer.parseInt(meet_id));

    model.addAttribute(au) ;
    model.addAttribute(meet);
    model.addAttribute(users);
    return "edit_meet";

    
}



//add postmapping constructors for submitting forms



}
