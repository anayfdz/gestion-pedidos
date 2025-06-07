package com.example.gestion_pedidos.exception;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(Long clientId) {
        super("Client not found with id: " + clientId);
    }
    
}
