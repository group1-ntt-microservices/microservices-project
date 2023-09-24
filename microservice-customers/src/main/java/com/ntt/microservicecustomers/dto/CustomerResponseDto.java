package com.ntt.microservicecustomers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date birthDate;
    private Long customerTypeId;
    private String customerTypeName;
}