package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dtos.RequestDTO.SellerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.SellerResponseDto;
import com.example.FlipCommerce.model.Product;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);


    String updateMobNo(String emailId, String newMobNo);

    List<SellerResponseDto> getByParticularCategory(Category category);

    List<ProductResponseDto> getAllProducts(String emailId);

    List<SellerResponseDto> getSellersWithHighestNumberOfProducts();

    List<SellerResponseDto> getSellersWithLowestNumberOfProducts();

    List<SellerResponseDto> getSellersWithCostliestProducts();

    List<SellerResponseDto> getSellersWithCheapestProducts();
}
