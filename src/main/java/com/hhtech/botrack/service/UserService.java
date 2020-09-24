package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.repository.RoleRepository;
import com.hhtech.botrack.repository.StatusRepository;
import com.hhtech.botrack.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;

import java.util.List;

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
    private StatusRepository statusRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByStatusNotDeleted() {
        return userRepository.findByStatusNotIn(Status.Type.DELETED.toStatus());
    }

    public User findOne(String id) {

        var user = userRepository.getOne(id);

        if (user == null) {
            throw new IllegalArgumentException("ID does not exist");
        }

        return user;
    }

    public void deleteOne(String id) {
        userRepository.deleteById(id);
    }
}
