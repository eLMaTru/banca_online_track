package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.Partner;
import com.hhtech.botrack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Partner repository.
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner,String> {

    /**
     * Find by status not in list.
     *
     * @param status the status
     * @return the list
     */
    List<Partner> findByStatusNotIn(Status... status);


}
