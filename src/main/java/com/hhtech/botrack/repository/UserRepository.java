package com.hhtech.botrack.repository;

import java.util.List;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByStatusNotIn(Status... status);

    User findByUsername(String username);

    default boolean userNameExists(String userName) {

        return findByUsername(userName) == null ? false : true;
    }
}
