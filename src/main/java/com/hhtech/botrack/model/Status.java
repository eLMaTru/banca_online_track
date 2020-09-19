package com.hhtech.botrack.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(length = 12)
    private String name;
    @Column(length = 40)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Status status = (Status) o;

        if (id != status.id)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return getId() + ":" + getName();
    }

    public Status(long id) {
    }

    public enum Type {

        ENABLED(1, "ENABLED"), DISABLED(2, "DISABLED"), DELETED(3, "DELETED"), PENDING(4, "PENDING"),
        COMPLETED(5, "COMPLETED"), CANCELED(6, "CANCELED"), REJECTED(7, "REJECTED");

        private final long id;
        private final String name;

        private Type(long id, String name) {
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
            return new Status(id);
        }

    }

}
