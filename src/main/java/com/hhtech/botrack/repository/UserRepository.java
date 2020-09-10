package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    default boolean userNameExists(String userName) {

        return findByUsername(userName) == null ? false : true;
    }
}
