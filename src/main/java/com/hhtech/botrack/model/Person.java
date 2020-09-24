package com.hhtech.botrack.model;

import java.util.Calendar;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@MappedSuperclass
public class Person extends Auditable<String> {

    @NotNull
    @Length(max = 11, min = 11)
    private String pdi;
    @NotNull
    @Length(max = 20, min = 3)
    private String name;
    @Length(max = 30, min = 3)
    private String lastName;
    private Calendar birthDay;
    @Length(max = 1, min = 1)
    private String gender;
    @Length(max = 10, min = 10)
    private String mobileNumber;

}
