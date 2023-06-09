package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import com.example.FlipCommerce.model.Item;

public interface ItemService {


    Item createItem(ItemRequestDto itemRequestDto);
}
