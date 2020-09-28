package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Banker;
import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.repository.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Banker service.
 */
@Service
public class BankerService {

    /**
     * bankerRepository
     * */
    private BankerRepository bankerRepository;


    /**
     * Instantiates a new Banker service.
     *
     * @param bankerRepository the banke repository
     */
    @Autowired
    public BankerService(BankerRepository bankerRepository) {
        this.bankerRepository = bankerRepository;
    }

    /**
     * Save.
     *
     * @param banker the banker
     */
    public void save(Banker banker) {
        bankerRepository.save(banker);
    }


    /**
     * Find one banker.
     *
     * @param id the id
     * @return the banker
     */
    public Banker findOne(String id) {

        var banker = bankerRepository.getOne(id);

        if (banker == null) {
            throw new IllegalArgumentException("ID does not exist");
        }

        return banker;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Banker> findAll() {
        return bankerRepository.findAll();
    }


    /**
     * Find by status not deleted list.
     *
     * @return the list
     */
    public List<Banker> findByStatusNotDeleted() {
        return bankerRepository.findByStatusNotIn(Status.Type.DELETED.toStatus());
    }



}
