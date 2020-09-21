package com.hhtech.botrack.repository;

import java.util.List;

import com.hhtech.botrack.model.Consortium;
import com.hhtech.botrack.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ConsortiumRepository extends JpaRepository<Consortium, String> {

    Consortium findByName(String name);

    List<Consortium> findByStatus(Status status);

    default boolean consortiumNameExists(String name) {

        return findByName(name) == null ? false : true;
    }
}
