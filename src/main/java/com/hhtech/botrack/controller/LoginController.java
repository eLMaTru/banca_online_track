package com.hhtech.botrack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@Controller
@Data
public class LoginController {

    @Autowired
    private RoleService roleService;
    private final List<Status> status = new ArrayList<>(2);

    @GetMapping("/owner/dashboard")
    public String superUserLogin(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roleService.findAll());
        status.clear();
        status.add(Status.Type.ENABLED.toStatus());
        status.add(Status.Type.DISABLED.toStatus());
        map.put("status", status);
        map.put("user", new User());

        model.addAllAttributes(map);
        return "super-user";
    }

    @GetMapping("/admin/dashboard")
    public String adminLogin(Model model) {

        return "admin";
    }
}
