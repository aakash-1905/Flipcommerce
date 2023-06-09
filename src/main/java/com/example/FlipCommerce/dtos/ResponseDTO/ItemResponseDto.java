package com.example.FlipCommerce.dtos.ResponseDTO;


import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    int quantityAdded;

    String productName;

    int price;


}
