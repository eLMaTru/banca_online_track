package com.hhtech.botrack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.hhtech.botrack.Util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "user")
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class User extends Auditable<String> {

    @Id
    @Column(name = "user_id", length = Util.UUID_LENGTH)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotBlank
    @Length(max = 20, min = 4)
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Length(max = 200, min = 6)
    @Column(nullable = false)
    private String password;

    @Transient
    @NotBlank
    private String passwordConfirm;

    @NotBlank
    @Length(max = 50, min = 6)
    @Pattern(regexp = Util.EMAIL_PATTERN, message = "Email incorrecto")
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private Role role;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull
    private Status status;

    public User() {
        super();
    }

}
