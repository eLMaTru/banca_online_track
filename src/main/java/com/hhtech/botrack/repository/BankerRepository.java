package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.Banker;
import com.hhtech.botrack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankerRepository extends JpaRepository<Banker,String> {

    List<Banker> findByStatusNotIn(Status... status);


}
