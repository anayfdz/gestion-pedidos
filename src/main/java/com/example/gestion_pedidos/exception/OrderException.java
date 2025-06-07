package com.example.gestion_pedidos.exception;

public class OrderException extends RuntimeException {
   public OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
