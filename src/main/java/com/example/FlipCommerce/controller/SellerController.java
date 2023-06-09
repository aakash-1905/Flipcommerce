package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dtos.RequestDTO.SellerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.SellerResponseDto;
import com.example.FlipCommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add-seller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity<>(sellerRequestDto, HttpStatus.CREATED);
    }


    // update the seller info based on email.

    @PutMapping("/update-mobNo")
    public ResponseEntity updateMobNo(@RequestParam String emailId, @RequestParam String newMobNo){

        String response = sellerService.updateMobNo(emailId,newMobNo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get all the sellers who sell products of a particular category
    @GetMapping("/get-seller-by-particular-category")
    public ResponseEntity getByParticularCategory(@RequestParam Category category){
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getByParticularCategory(category);
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.FOUND);
    }


    // get all the products sold by a seller in a category

    @GetMapping("/get-all-products-by-a-seller")
    public ResponseEntity getAllProducts(@RequestParam String emailId){

        List<ProductResponseDto> productResponseDtoList = sellerService.getAllProducts(emailId);
        return new ResponseEntity<>(productResponseDtoList,HttpStatus.OK);

    }

    // seller with the highest number of products
    @GetMapping("/get-sellers-with-highest-products")
    public ResponseEntity getSellersWithHighestNumberOfProducts(){

        List<SellerResponseDto> sellerResponseDtoList = sellerService.getSellersWithHighestNumberOfProducts();
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.OK);

    }

    // seller with minimum number of products

    @GetMapping("/get-sellers-with-lowest-products")
    public ResponseEntity getSellersWithLowestNumberOfProducts(){

        List<SellerResponseDto> sellerResponseDtoList = sellerService.getSellersWithLowestNumberOfProducts();
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.OK);

    }

    // seller(s) selling the costliest product
    @GetMapping("/get-sellers-with-costliest-products")
    public ResponseEntity getSellersWithCostliestProducts(){

        List<SellerResponseDto> sellerResponseDtoList = sellerService.getSellersWithCostliestProducts();
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.OK);

    }

    // seller(s) selling the cheapest product
    @GetMapping("/get-sellers-with-cheapest-products")
    public ResponseEntity getSellersWithCheapestProducts(){

        List<SellerResponseDto> sellerResponseDtoList = sellerService.getSellersWithCheapestProducts();
        return new ResponseEntity<>(sellerResponseDtoList,HttpStatus.OK);

    }
}
