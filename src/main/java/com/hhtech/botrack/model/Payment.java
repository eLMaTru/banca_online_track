package com.hhtech.botrack.model;

import java.util.Calendar;

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
@Table(name = "payment")
@Data
public class Payment extends Auditable<String> {

    @Id
    @Column(name = "payment_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column(name = "paid_on_date")
    private Calendar paidOnDate;
    @Column(name = "amount_to_pay", nullable = false)
    private double amountToPay;
    @Column(name = "amount_to_paid")
    private double amountPaid;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}
