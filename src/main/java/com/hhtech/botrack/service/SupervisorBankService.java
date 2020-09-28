package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.SupervisorBank;
import com.hhtech.botrack.repository.SupervisorBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Supervisor bank service.
 */
@Service
public class SupervisorBankService {

    private SupervisorBankRepository supervisorBankRepository;

    /**
     * Instantiates a new Supervisor bank service.
     *
     * @param supervisorBankRepository the supervisor bank repository
     */
    @Autowired
    public  SupervisorBankService(SupervisorBankRepository supervisorBankRepository){
        this.supervisorBankRepository = supervisorBankRepository;
    }


    /**
     * Save.
     *
     * @param supervisorBank the supervisor bank
     */
    public void save(SupervisorBank supervisorBank) {
        supervisorBankRepository.save(supervisorBank);
    }


    /**
     * Find one supervisor bank.
     *
     * @param id the id
     * @return the supervisor bank
     */
    public SupervisorBank findOne(String id) {

        var supervisorBank = supervisorBankRepository.getOne(id);

        if (supervisorBank == null) {
            throw new IllegalArgumentException("ID does not exist");
        }

        return supervisorBank;
    }


    /**
     * Find all list.
     *
     * @return the list
     */
    public List<SupervisorBank> findAll() {
        return supervisorBankRepository.findAll();
    }


    /**
     * Find by status not deleted list.
     *
     * @return the list
     */
    public List<SupervisorBank> findByStatusNotDeleted() {
        return supervisorBankRepository.findByStatusNotIn(Status.Type.DELETED.toStatus());
    }



}
