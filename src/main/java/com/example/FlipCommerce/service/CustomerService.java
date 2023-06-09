package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dtos.RequestDTO.CustomerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> customerKOrders(int k);

    // List<CustomerResponseDto> getFemaleCustomerAgeBetween(int fromAge, int toAge);
}
