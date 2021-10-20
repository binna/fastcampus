package com.fastcompaus.mycontact.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {
    private String name;
    private String hobby;
    private String address;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;
}
