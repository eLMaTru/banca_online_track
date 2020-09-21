package com.hhtech.botrack.service;

import com.hhtech.botrack.model.Role;
import com.hhtech.botrack.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Role service.
 */
@Service
public class RoleService {

    /**
     * role repository
     * */
    private RoleRepository roleRepository;

    /**
     * Instantiates a new Role service.
     *
     * @param roleRepository the role repository
     */
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
