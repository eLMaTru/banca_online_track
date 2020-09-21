package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}