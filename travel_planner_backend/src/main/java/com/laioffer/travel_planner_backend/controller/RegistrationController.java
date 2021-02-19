package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.User;
import com.laioffer.travel_planner_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(@RequestBody User user,
                                         BindingResult result) {

        if (result.hasErrors()) {
            return "Register error";
        }
        userService.addUser(user);
        return "redirect:/index";
    }
}
