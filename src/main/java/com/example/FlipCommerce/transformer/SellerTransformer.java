package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.dtos.RequestDTO.SellerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.SellerResponseDto;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.service.SellerService;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }


}
