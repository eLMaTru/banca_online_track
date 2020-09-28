package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.SupervisorBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisorBankRepository extends JpaRepository<SupervisorBank,String> {

    List<SupervisorBank> findByStatusNotIn(Status... status);

}
