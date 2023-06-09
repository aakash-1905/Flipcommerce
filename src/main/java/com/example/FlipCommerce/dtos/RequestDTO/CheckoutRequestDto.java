package com.example.FlipCommerce.dtos.RequestDTO;


import com.example.FlipCommerce.dtos.ResponseDTO.ItemResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutRequestDto {

    String customerEmailId;

    String cardNo;

    int cvv;


}
