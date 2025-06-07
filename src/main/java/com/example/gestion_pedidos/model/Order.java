package com.example.gestion_pedidos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @NotNull
    private Long customerId;

    @NotNull
    @Size(min = 1)
    private List<OrderItem> items;

    @NotNull
    @Positive
    private BigDecimal total;

    private LocalDateTime createdAt;

      @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        @NotNull
        private Long productId;

        @NotNull
        @Positive
        private Integer quantity;
    }
} 
