package com.hhtech.botrack.controller;

import com.hhtech.botrack.service.SecurityService;
import com.hhtech.botrack.service.UserService;
import com.hhtech.botrack.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.Data;

@Controller
@Data // Lombok: adds getters and setters
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/login")
    public String login() {
        System.out.println("On UserController.login");

        return "login";
    }

}