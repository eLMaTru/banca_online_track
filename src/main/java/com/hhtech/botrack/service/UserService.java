package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Role;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.repository.RoleRepository;
import com.hhtech.botrack.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data // Lombok: adds getters and setters
public class UserService {

    public UserService() {
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(new Role(1L, "ENABLED", "Item is Enabled"));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean userNameExist(String username) {
		return userRepository.userNameExists(username);
	}

}
