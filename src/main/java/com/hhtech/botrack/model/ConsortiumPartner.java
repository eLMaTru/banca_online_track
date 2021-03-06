package com.hhtech.botrack.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hhtech.botrack.Util;

@Entity
@Table(name = "consortium_partner")
@Data
public class ConsortiumPartner {

    @Id
    @Column(name = "consortium_partner_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consortium_id", nullable = false)
    private Consortium consortium;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

}
