package com.scm.controllers;

import com.scm.helpers.Helper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger=LoggerFactory.getLogger(UserController.class);
    //user dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}",username);
        //get user from db and show on page

        return "user/profile";
    }


    //user add contacts page

    //user view contacts

    //user edit contacts

    //user delete contacts

    //user search contacts
}
