package com.hhtech.botrack.model;

import java.util.Calendar;

import lombok.Data;

@Data
public class Person extends Auditable<String> {

    private String id;
    private String name;
    private String lastName;
    private Calendar birthDay;
    private String gender;
    private String mobileNumber;

}
