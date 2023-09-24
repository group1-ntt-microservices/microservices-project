package com.ntt.microservicecustomers.mapper;

import com.ntt.microservicecustomers.dto.CustomerResponseDto;
import com.ntt.microservicecustomers.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(source = "customerType.id", target = "customerTypeId"),
            @Mapping(source = "customerType.typeName", target = "customerTypeName"),
            @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd-MM-yyyy")
    })
    CustomerResponseDto toCustomerResponseDto(Customer customer);
    List<CustomerResponseDto> toCustomerResponseDtoList(List<Customer> customers);
}
