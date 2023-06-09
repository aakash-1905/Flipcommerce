package com.example.FlipCommerce.controller;


import com.example.FlipCommerce.Enum.CardType;
import com.example.FlipCommerce.Exception.CustomerNotFoundException;
import com.example.FlipCommerce.dtos.RequestDTO.CardRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CardResponseDto;
import com.example.FlipCommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add-card")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    // tell me the card type which exists max number of times.
    @GetMapping("/max-card-type")
    public ResponseEntity getMaxCardType(){
        List<String> cardType = cardService.getMaxCardType();
        return new ResponseEntity<>(String.valueOf(cardType),HttpStatus.FOUND);
    }

    // tell me the card type which exists min number of times.
    @GetMapping("/min-card-type")
    public ResponseEntity getMinCardType(){
        List<String> cardType = cardService.getMinCardType();
        return new ResponseEntity<>(String.valueOf(cardType),HttpStatus.FOUND);
    }

}
