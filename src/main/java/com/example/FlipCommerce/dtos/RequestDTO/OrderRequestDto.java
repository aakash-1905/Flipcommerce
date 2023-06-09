package com.example.FlipCommerce.dtos.RequestDTO;


import com.example.FlipCommerce.dtos.ResponseDTO.ItemResponseDto;
import com.example.FlipCommerce.model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {

    String emailId;

    int productId;

    String cardNo;

    int cvv;

    int requiredQuantity;


}
