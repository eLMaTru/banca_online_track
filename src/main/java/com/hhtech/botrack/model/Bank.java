package com.hhtech.botrack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bank")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Bank {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(length = 50)
    private String code;
    @Column(length = 80)
    private String address;
    @Column(length = 100)
    private String location;
    private double rent_cost;
    @Column(length = 1, name = "pay_frequency")
    private String payFrequency;
    @Column(name = "pay_on_date")
    private LocalDate payOnDate;
    @Column(name = "phone_number", length = 10)
    private String phone_number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}
