package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Exception.SellerNotFoundException;
import com.example.FlipCommerce.dtos.RequestDTO.ProductRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){


        try {
            ProductResponseDto productResponseDto = productService.addSeller(productRequestDto);
            return  new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);

        }catch (SellerNotFoundException e){
           // return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getProductsByCategoryAndPrice(@PathVariable Category category, @PathVariable int price){

        List<ProductResponseDto> productResponseDtoList = productService.getAllProductsByCategoryAndPrice(category,price);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get all the products of a category
    @GetMapping("/get-product-of-a-category")
    public ResponseEntity getProductOfACategory(@RequestParam String category){
        List<ProductResponseDto> productResponseDtoList = productService. getProductOfACategory(category);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get all the products in a category who have price greater than 500
    @GetMapping("/get-product-of-a-category-and-price-greater-than")
    public ResponseEntity getProductOfACategoryPriceGreater(@RequestParam String category,@RequestParam int minPrice){
        List<ProductResponseDto> productResponseDtoList = productService.getProductOfACategoryPriceGreater(category,minPrice);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get the top 5 cheapest products in a category
    @GetMapping("/get-the-top-k-cheapest-products-category")
    public ResponseEntity getKCheapestProductACategory(@RequestParam String category,@RequestParam int k){
        List<ProductResponseDto> productResponseDtoList = productService.getKCheapestProductACategory(category,k);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get top 5 costliest products in a category
    @GetMapping("/get-the-top-k-costliest-products-category")
    public ResponseEntity getKCostliestProductACategory(@RequestParam String category,@RequestParam int k){
        List<ProductResponseDto> productResponseDtoList = productService.getKCostliestProductACategory(category,k);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get all the products of a seller based on email-id

    @GetMapping("/get-all-products-of-a-seller")
    public ResponseEntity getAllProductOfASeller(@RequestParam String emailId){
        List<ProductResponseDto> productResponseDtoList = productService.getAllProductOfASeller(emailId);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }

    // get all the out-of-stock products for a particular category
    @GetMapping("/get-out-of-stock-products-category")
    public ResponseEntity getAllOutOfProductOfACategory(@RequestParam String category){
        List<ProductResponseDto> productResponseDtoList = productService.getAllOutOfProductOfACategory(category);

        return new ResponseEntity<>(productResponseDtoList,HttpStatus.FOUND);

    }


}
