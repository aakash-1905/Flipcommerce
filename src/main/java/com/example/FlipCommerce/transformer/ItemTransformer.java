package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ItemResponseDto;
import com.example.FlipCommerce.model.Item;

public class ItemTransformer {


    public static Item ItemRequestDtoToItem( int requiredQuantity) {


        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();

    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();

    }
}
