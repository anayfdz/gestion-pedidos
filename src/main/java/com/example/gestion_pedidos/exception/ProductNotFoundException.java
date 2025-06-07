package com.example.gestion_pedidos.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("One or more products not found");
    }
}
