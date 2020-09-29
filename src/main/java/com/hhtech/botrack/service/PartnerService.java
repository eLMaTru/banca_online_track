package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Partner;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PartnerService{

    /**
     * partnerRepository
     * */
    private PartnerRepository partnerRepository;


    /**
     * Instantiates a new partner  service.
     *
     * @param partnerRepository the partner repository
     */
    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    /**
     * Save.
     *
     * @param partner the payment
     */
    public void save(Partner payment) {
        partnerRepository.save(payment);
    }


    /**
     * Find one partner.
     *
     * @param id the id
     * @return the payment
     */
    public Partner findOne(String id) {

        var partner = partnerRepository.getOne(id);

        if (partner == null) {
            throw new IllegalArgumentException("ID does not exist");
        }

        return partner;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }


    /**
     * Find by status not deleted list.
     *
     * @return the list
     */
    public List<Partner> findByStatusNotDeleted() {
        return partnerRepository.findByStatusNotIn(Status.Type.DELETED.toStatus());
    }

}
