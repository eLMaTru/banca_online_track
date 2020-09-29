package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Banker;
import com.hhtech.botrack.model.Payment;
import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type payment service.
 */
@Service
public class PaymentService {

    /**
     * paymentRepository
     * */
    private PaymentRepository paymentRepository;


    /**
     * Instantiates a new Payment service.
     *
     * @param paymentRepository the banke repository
     */
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Save.
     *
     * @param payment the payment
     */
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }


    /**
     * Find one payment.
     *
     * @param id the id
     * @return the payment
     */
    public Payment findOne(String id) {

        var payment = paymentRepository.getOne(id);

        if (payment == null) {
            throw new IllegalArgumentException("ID does not exist");
        }

        return payment;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }


    /**
     * Find by status not deleted list.
     *
     * @return the list
     */
    public List<Payment> findByStatusNotDeleted() {
        return paymentRepository.findByStatusNotIn(Status.Type.DELETED.toStatus());
    }

}
