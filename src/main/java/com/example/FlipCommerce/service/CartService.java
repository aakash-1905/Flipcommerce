package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dtos.RequestDTO.CheckoutRequestDto;
import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CartResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.OrderResponseDto;
import com.example.FlipCommerce.model.Item;

public interface CartService {

    CartResponseDto addCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkoutCart(CheckoutRequestDto checkoutRequestDto);
}
