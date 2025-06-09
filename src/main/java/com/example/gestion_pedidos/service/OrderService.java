package com.example.gestion_pedidos.service;

import com.example.gestion_pedidos.dto.ItemOrderRequest;
import com.example.gestion_pedidos.dto.OrderRequest;
import com.example.gestion_pedidos.exception.ClientNotFoundException;
import com.example.gestion_pedidos.exception.OrderException;
import com.example.gestion_pedidos.exception.ProductNotFoundException;
import com.example.gestion_pedidos.model.Order;
import com.example.gestion_pedidos.model.Product;
import com.example.gestion_pedidos.outbox.TransactionalOutbox;
import com.example.gestion_pedidos.repository.CustomerRepository;
import com.example.gestion_pedidos.repository.OrderRepository;
import com.example.gestion_pedidos.repository.ProductRepository;
import com.example.gestion_pedidos.outbox.OutboxEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository clientRepository;
    private final ProductRepository productRepository;
    private final TransactionalOutbox outbox;

    public Mono<Order> createOrder(OrderRequest request) {
        return clientRepository.existsById(request.getClientId())
            .flatMap(clientExists -> {
                if (!clientExists) {
                    return Mono.error(new ClientNotFoundException(request.getClientId()));
                }

                return Flux.fromIterable(request.getProducts())
                    .flatMap(item -> productRepository.findById(item.getProductId()))
                    .collectList()
                    .flatMap(products -> {
                        if (products.size() != request.getProducts().size()) {
                            return Mono.error(new ProductNotFoundException());
                        }

                        BigDecimal total = calculateTotal(request.getProducts(), products);

                        Order order = Order.builder()
                            .customerId(request.getClientId())
                            .items(request.getProducts().stream()
                                .map(item -> new Order.OrderItem(
                                    item.getProductId(),
                                    item.getQuantity()))
                                .collect(Collectors.toList()))
                            .total(total)
                            .createdAt(LocalDateTime.now())
                            .build();

                        return orderRepository.save(order)
                            .flatMap(savedOrder -> 
                                outbox.registerEvent(new OutboxEvent(
                                    "ORDER_CREATED",
                                    savedOrder.getId(),
                                    savedOrder
                                )).thenReturn(savedOrder)
                            );
                    });
            })
            .onErrorMap(ex -> new OrderException("Error creating order", ex));
    }
    public Mono<Order> getOrderById(String id) {
    return orderRepository.findById(id);
}

    private BigDecimal calculateTotal(
        java.util.List<ItemOrderRequest> items,
        java.util.List<Product> products) {

        return items.stream()
            .<BigDecimal>map(item -> {
                Product product = products.stream()
                    .filter(p -> p.getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException());
                return product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
