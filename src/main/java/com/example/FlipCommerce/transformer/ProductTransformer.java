package com.example.FlipCommerce.transformer;

import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.dtos.RequestDTO.ProductRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return  Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
