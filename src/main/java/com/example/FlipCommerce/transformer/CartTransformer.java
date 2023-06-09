package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dtos.ResponseDTO.CartResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ItemResponseDto;
import com.example.FlipCommerce.model.Cart;
import com.example.FlipCommerce.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart) {

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for(Item item : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }


        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemResponseDtos)
                .build();



    }
}
