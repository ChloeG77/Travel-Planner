package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String sayIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, @RequestBody User user) {

        if (error != null) {
            System.out.println("error: " + error);
            return error  + "Invalid username and Password";
        }

        if (logout != null) {
            return "logout, " + "You have logged out successfully";
        }

        return "emailId:" + user.getEmailId();
    }

    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public String sayAbout() {
        return "aboutUs";
    }
}
