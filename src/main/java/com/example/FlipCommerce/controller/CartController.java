package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dtos.RequestDTO.CheckoutRequestDto;
import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CartResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.OrderResponseDto;
import com.example.FlipCommerce.model.Item;
import com.example.FlipCommerce.service.CartService;
import com.example.FlipCommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try {
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addCart(item,itemRequestDto);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")// correct the bug
    public ResponseEntity checkoutCart(@RequestBody CheckoutRequestDto checkoutRequestDto){

        try {
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // add the functionality of email sending in direct order and checkout cart
    // kunaljindal995@gmail.com

    // integrate swagger
}
