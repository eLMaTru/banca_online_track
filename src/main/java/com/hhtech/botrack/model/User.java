package com.hhtech.botrack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "user")
@Data // Lombok: adds getters and setters
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String username;
    @Transient
    private String passwordConfirm;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Role role;

}