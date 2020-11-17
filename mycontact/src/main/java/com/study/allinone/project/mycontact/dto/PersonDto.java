package com.study.allinone.project.mycontact.dto;

import com.study.allinone.project.mycontact.domain.dto.Birthday;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {

    private String name;

    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    private String phoneNumber;

}
