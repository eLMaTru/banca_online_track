package com.hhtech.botrack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hhtech.botrack.Util;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "partner_bank")
@Data
public class PartnerBank {

    @Id
    @Column(name = "partner_bank_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

}
