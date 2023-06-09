package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dtos.RequestDTO.CheckoutRequestDto;
import com.example.FlipCommerce.dtos.RequestDTO.OrderRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.OrderResponseDto;
import com.example.FlipCommerce.service.CartService;
import com.example.FlipCommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try {
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    // get top 5 orders with the highest order value

    @GetMapping("/get-top-k-orders-with-max-order-value")
    public ResponseEntity getTopKOrdersMaxOrderValue(@RequestParam int k){
        List<OrderResponseDto> orderResponseDtoList = orderService.getTopKOrdersMaxOrderValue(k);
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.FOUND);
    }

    // all the orders of a particular customer
    @GetMapping("/get-orders-of-a-customer")
    public ResponseEntity getOrderOfACustomer(@RequestParam String emailId){
        List<OrderResponseDto> orderResponseDtoList = orderService.getOrderOfACustomer(emailId);
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.FOUND);
    }

    // top 5 orders of a customer based on cost
    @GetMapping("/get-top-k-orders-of-a-customer")
    public ResponseEntity getTopKOrderOfACustomer(@RequestParam String emailId,@RequestParam int k){
        List<OrderResponseDto> orderResponseDtoList = orderService.getTopKOrderOfACustomer(emailId,k);
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.FOUND);
    }

    // top 5 recently ordered orders of a customer
    @GetMapping("/top-k-recently-ordered-customer")
    public ResponseEntity getKRecentlyOrderCustomer(@RequestParam String emailId,int k){
        List<OrderResponseDto> orderResponseDtoList = orderService.getKRecentlyOrderCustomer(emailId,k);
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.FOUND);
    }

}
