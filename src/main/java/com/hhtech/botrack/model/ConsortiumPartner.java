package com.hhtech.botrack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "consortium_partner")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class ConsortiumPartner {

    @Id
    @Column(name = "consortium_partner_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consortium_id")
    private Consortium consortium;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    private Partner partner;

}
