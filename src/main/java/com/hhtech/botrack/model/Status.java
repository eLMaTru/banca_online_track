package com.hhtech.botrack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "status_id")
    private Long id;

    @Column(length = 12, unique = true)
    private String name;
    @Column(length = 40)
    private String description;

    @Override
    public String toString() {
        return getId() + ":" + getName();
    }

    public Status(Long id) {
        this.id = id;
    }

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public enum Type {

        ENABLED(1, "ENABLED"), DISABLED(2, "DISABLED"), DELETED(3, "DELETED"), PENDING(4, "PENDING"),
        COMPLETED(5, "COMPLETED"), CANCELED(6, "CANCELED"), REJECTED(7, "REJECTED");

        private final long id;
        private final String name;

        Type(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public long getId() {
            return id;
        }

        public Status toStatus() {
            return new Status(id, name);
        }

    }

}
