package com.hhtech.botrack.repository;

import com.hhtech.botrack.model.Payment;
import com.hhtech.botrack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Payment repository.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {

    /**
     * Find by status not in list.
     *
     * @param status the status
     * @return the list
     */
    List<Payment> findByStatusNotIn(Status... status);

}
