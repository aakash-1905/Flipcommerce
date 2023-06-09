package com.example.FlipCommerce.controller;


import com.example.FlipCommerce.dtos.RequestDTO.CustomerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CustomerResponseDto;
import com.example.FlipCommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity<>(customerRequestDto, HttpStatus.CREATED);

    }

    // customers who have ordered at least 'k' orders
    @GetMapping("/customer-k-order")
    public ResponseEntity customerKOrders(@RequestParam int k){
        List<CustomerResponseDto> customerResponseDtoList = customerService.customerKOrders(k);
        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.OK);
    }

    // get all female customers between age 20-30


//    @GetMapping("/get-female-customers-age-between")
//    public ResponseEntity getFemaleCustomerAgeBetween(@RequestParam int fromAge, @RequestParam int toAge ){
//        List<CustomerResponseDto> customerResponseDtoList = customerService.getFemaleCustomerAgeBetween(fromAge,toAge);
//        return new ResponseEntity<>(customerResponseDtoList,HttpStatus.OK);
//    }


    // get all male customers age less than 45



}
