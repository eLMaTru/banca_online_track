package com.hhtech.botrack.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hhtech.botrack.Util;

@Entity
@Table(name = "partner")
@Data
public class Partner extends Person {

    @Id
    @Column(name = "partner_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "consortium_id")
    private Consortium consortium;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(name = "partner_user", joinColumns = { @JoinColumn(name = "partner_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "partner_bank", joinColumns = { @JoinColumn(name = "partner_id") }, inverseJoinColumns = {
            @JoinColumn(name = "bank_id") })
    private List<Bank> banks;

    @ManyToMany
    @JoinTable(name = "partner_banker", joinColumns = { @JoinColumn(name = "partner_id") }, inverseJoinColumns = {
            @JoinColumn(name = "banker_id") })
    private List<Banker> bankers;
}
