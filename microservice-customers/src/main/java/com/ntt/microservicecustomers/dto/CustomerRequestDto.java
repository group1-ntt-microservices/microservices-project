package com.ntt.microservicecustomers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequestDto {
    private String documentNumber;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private Date birthDate;
    private Long customerTypeId;
}