package com.example.gestion_pedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    private Long clientId;

    @NotNull
    @Size(min = 1)
    private List<ItemOrderRequest> products;
    
}
