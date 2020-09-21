package com.hhtech.botrack.controller;

import com.hhtech.botrack.model.Role;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.RoleService;
import com.hhtech.botrack.service.SecurityService;
import com.hhtech.botrack.service.UserService;
import com.hhtech.botrack.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Data // Lombok: adds getters and setters
public class UserController {

    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService,
                          SecurityService securityService,UserValidator userValidator){
        this.userService = userService;
        this.roleService = roleService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/owner")
    public String superUser() {// Solo para probar el login
        return "super_user";
    }

    @GetMapping("/users/adduser")
    public String addUser(Model model){

        Map<String,Object> map = new HashMap<>();
        map.put("roles",roleService.findAll());
        map.put("user",new User());

        model.addAllAttributes(map);

        return "/users/add-user";
    }

    @GetMapping("/users/")
    public String index(Model model){

        model.addAttribute("users", userService.findAll());
        return "/users/index";

    }


    @PostMapping("/users/saveuser")
    public String saveUser(@Valid User user, BindingResult result, Model model) {

        model.addAttribute("user",user);
        model.addAttribute("roles",roleService.findAll());

        if (result.hasErrors()) {

            return "/users/add-user";
        }

        userService.save(user);

        return "redirect:/users/";
    }

    @GetMapping("/users/edituser/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("roles",roleService.findAll());

        return "/users/edit-user";
    }

    @GetMapping("/users/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {

        userService.deleteOne(id);

        return "redirect:/users/";
    }
}