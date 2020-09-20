package com.hhtech.botrack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import com.hhtech.botrack.Util;

import java.time.LocalDate;

@Entity
@Table(name = "bank")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Bank extends Auditable<String> {

    @Id
    @Column(name = "bank_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(length = 10)
    private String code;
    @Column(length = 80)
    private String address;
    @Column(length = 100)
    private String location;
    @Column(name = "rent_cost")
    private double rentCost;
    @Column(length = 1, name = "pay_frequency")
    private String payFrequency;
    @Column(name = "pay_on_date")
    private LocalDate payOnDate;
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}
