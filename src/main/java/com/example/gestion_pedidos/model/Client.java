package com.example.gestion_pedidos.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("client")
public class Client {
    @Id
    private Long id;

    @NotBlank
    private String nombre;

    @Email
    private String email;
    
}
