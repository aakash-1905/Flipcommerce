package com.example.FlipCommerce.dtos.ResponseDTO;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    int cartTotal;

    String customerName;

    List<ItemResponseDto> items = new ArrayList<>();

}
