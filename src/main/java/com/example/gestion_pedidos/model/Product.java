package com.example.gestion_pedidos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("product")
public class Product {
    @Id
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    @Positive
    private BigDecimal precio;
    
}
