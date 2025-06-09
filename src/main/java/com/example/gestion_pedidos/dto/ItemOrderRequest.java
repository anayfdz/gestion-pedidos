package com.example.gestion_pedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderRequest {
    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private Integer quantity;
}
