package com.example.FlipCommerce.Exception;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(String message) {
        super(message);
    }
}
