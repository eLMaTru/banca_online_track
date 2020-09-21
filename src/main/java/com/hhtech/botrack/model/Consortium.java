package com.hhtech.botrack.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.*;

import com.hhtech.botrack.Util;

@Entity
@Table(name = "consortium")
@Data
public class Consortium extends Auditable<String> {

    @Id
    @Column(name = "consortium_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(name = "consortium_partner", joinColumns = {
            @JoinColumn(name = "consortium_id") }, inverseJoinColumns = { @JoinColumn(name = "partner_id") })
    private List<Partner> partners;

}
