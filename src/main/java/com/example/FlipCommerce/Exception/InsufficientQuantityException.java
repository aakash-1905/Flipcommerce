package com.example.FlipCommerce.Exception;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
