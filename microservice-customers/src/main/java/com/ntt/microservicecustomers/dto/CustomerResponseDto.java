package com.ntt.microservicecustomers.dto;

import lombok.*;

@Getter
@Setter
public class CustomerResponseDto {
    private Long id;
    private String documentNumber;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String birthDate;
    private Long customerTypeId;
    private String customerTypeName;
}