package com.hhtech.botrack.controller;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.RoleService;
import com.hhtech.botrack.service.SecurityService;
import com.hhtech.botrack.service.UserService;
import com.hhtech.botrack.validation.UserValidator;

import org.springframework.security.core.annotation.CurrentSecurityContext;
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
public class OwnerController {

    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    private RoleService roleService;

    private final List<Status> status = new ArrayList<>(2);

    public OwnerController(UserService userService, RoleService roleService, SecurityService securityService,
            UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }



    @GetMapping("/owner/users")
    public String viewUsers(Model model, @CurrentSecurityContext(expression = "authentication.name") String username) {
        model.addAttribute("username", username);
        model.addAttribute("users", userService.findByStatusNotDeleted());

        Map<String, Object> map = new HashMap<>();
        map.put("roles", roleService.findAll());
        status.clear();
        status.add(Status.Type.ENABLED.toStatus());
        status.add(Status.Type.DISABLED.toStatus());
        map.put("status", status);
        map.put("user", new User());

        model.addAllAttributes(map);

        return "user";
    }

    @PostMapping("/owner/users/save")
    public String saveUser(@Valid User user, BindingResult result, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());

        if (result.hasErrors()) {

            return "redirect:/owner/users?error=true";
        }

        userService.save(user);

        return "redirect:/owner/users";
    }

    @GetMapping("/owner/users/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("roles", roleService.findAll());
        status.clear();
        status.add(Status.Type.ENABLED.toStatus());
        status.add(Status.Type.DISABLED.toStatus());
        model.addAttribute("status", status);
        model.addAttribute("users", userService.findByStatusNotDeleted());
        model.addAttribute("edit", true);

        Map<String, Object> map = new HashMap<>();
        map.put("roles", roleService.findAll());
        map.put("user", userService.findOne(id));

        model.addAllAttributes(map);

        return "user";
    }

    @GetMapping("/owner/users/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {

        User user = userService.findOne(id);
        user.setStatus(Status.Type.DELETED.toStatus());
        userService.save(user);

        return "redirect:/owner/users/";
    }
}