package com.example.gestion_pedidos.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("clients")
public class Client {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;
    
}
