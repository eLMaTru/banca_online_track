package com.hhtech.botrack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data // Lombok: adds getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_id")
    private Long id;

    private String name;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role = (Role) o;

        if (id != role.id)
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

    public Role(long id) {
    }

    public enum Type {

        ADMIN(1, "ADMIN"), USER(2, "USER"), SUPERVISOR(3, "SUPERVISOR"), OWNER(4, "OWNER");

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

        public Role toRole() {
            return new Role(id);
        }

    }

}
