package com.hhtech.botrack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "consortium")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class Consortium extends Auditable<String>{

    @Id
    @Column(name = "consortium_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

}
