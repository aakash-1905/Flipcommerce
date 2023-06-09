package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dtos.RequestDTO.ProductRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.model.Product;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addSeller(ProductRequestDto productRequestDto);

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category,int price);

    List<ProductResponseDto> getProductOfACategory(String category);

    List<ProductResponseDto> getProductOfACategoryPriceGreater(String category, int minPrice);

    List<ProductResponseDto> getKCheapestProductACategory(String category, int k);

    List<ProductResponseDto> getKCostliestProductACategory(String category, int k);

    List<ProductResponseDto> getAllProductOfASeller(String emailId);

    List<ProductResponseDto> getAllOutOfProductOfACategory(String category);
}
