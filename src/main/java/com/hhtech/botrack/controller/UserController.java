package com.hhtech.botrack.controller;

import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.SecurityService;
import com.hhtech.botrack.service.UserService;
import com.hhtech.botrack.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Data // Lombok: adds getters and setters
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/owner")
    public String superUser() {// Solo para probar el login
        return "super_user";
    }

    @GetMapping("/users/adduser")
    public String addUser(){
        return "/users/add-user";
    }

    @GetMapping("/users/")
    public String index(Model model){

        model.addAttribute("users", userService.findAll());
        return "/users/index";

    }


    /*@PostMapping("/user/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        //userRepository.save(user);
        User user1 = new User();
        user1.setUsername("test 1");
        List<User> test = new ArrayList<>();
        test.add(user1);

        //model.addAttribute("users", userRepository.findAll());
        model.addAttribute("users", test);

        return "redirect:/index";
    }*/

}