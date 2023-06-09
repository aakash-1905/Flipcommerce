package com.example.FlipCommerce.dtos.RequestDTO;

import com.example.FlipCommerce.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String sellerEmailId;// from seller

    String name;

    Integer price;

    Category category;

    Integer quantity;

}
