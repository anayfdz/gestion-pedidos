package com.example.gestion_pedidos.controller;

import com.example.gestion_pedidos.dto.OrderRequest;
import com.example.gestion_pedidos.model.Order;
import com.example.gestion_pedidos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<Order>> createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request)
            .map(order -> ResponseEntity.created(URI.create("/api/orders/" + order.getId()))
            .body(order));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Order>> getOrder(@PathVariable String id) {
        return orderService.getOrderById(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
