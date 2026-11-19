package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller 
public class index_controller {
    


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
    return "view-meet";

    
}

@GetMapping("edit-meet")
public String edit_meet(@RequestParam String meet_id, Model model) {
    return "edit_meet";

    
}

}
