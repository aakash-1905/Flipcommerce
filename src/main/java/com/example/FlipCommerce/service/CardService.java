package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.CardType;
import com.example.FlipCommerce.dtos.RequestDTO.CardRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CardResponseDto;

import java.util.List;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto);

    List<String> getMaxCardType();

    List<String> getMinCardType();
}
