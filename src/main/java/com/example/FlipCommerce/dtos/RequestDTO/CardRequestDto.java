package com.example.FlipCommerce.dtos.RequestDTO;

import com.example.FlipCommerce.Enum.CardType;
import com.example.FlipCommerce.model.Customer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {


    String emailId;

    String cardNo;

    Integer cvv;

    CardType cardType;

    Date validTill;


}
