package com.hhtech.botrack.service;

import java.util.List;

import com.hhtech.botrack.model.Consortium;
import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.repository.ConsortiumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@Data
@Log4j2
public class ConsortiumService {

    @Autowired
    private ConsortiumRepository consortiumRepository;

    public boolean save(Consortium consortium, Status status) {
        boolean saved = true;
        if (consortium != null && !consortiumNameExist(consortium.getName())) {
            consortiumRepository.save(consortium);
        } else {
            saved = false;
            log.error("Consortium name {} already exist", consortium.getName());
        }

        return saved;
    }

    public Consortium findByName(String name) {
        return consortiumRepository.findByName(name);
    }

    public List<Consortium> findAll() {
        return consortiumRepository.findAll();
    }

    public List<Consortium> findByStatus(Status status) {
        return consortiumRepository.findByStatus(status);
    }

    private boolean consortiumNameExist(String name) {
        return consortiumRepository.consortiumNameExists(name);
    }

}