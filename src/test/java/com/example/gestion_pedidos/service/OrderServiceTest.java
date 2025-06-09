package com.example.gestion_pedidos.service;

import com.example.gestion_pedidos.dto.ItemOrderRequest;
import com.example.gestion_pedidos.dto.OrderRequest;
import com.example.gestion_pedidos.model.Order;
import com.example.gestion_pedidos.model.Product;
import com.example.gestion_pedidos.outbox.TransactionalOutbox;
import com.example.gestion_pedidos.repository.CustomerRepository;
import com.example.gestion_pedidos.repository.OrderRepository;
import com.example.gestion_pedidos.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private ProductRepository productRepository = mock(ProductRepository.class);
    private TransactionalOutbox outbox = mock(TransactionalOutbox.class);
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderService = new OrderService(orderRepository, customerRepository, productRepository, outbox);
    }
    @Test
    void createOrder_returnsOrder_whenDataIsValid() {
        Long clientId = 1L;
        Long productId = 10L;

        OrderRequest request = new OrderRequest(clientId, List.of(
            new ItemOrderRequest(productId, 2)
        ));
        Product product = new Product();
        product.setId(productId);
        product.setPrice(BigDecimal.valueOf(5));

        when(customerRepository.existsById(clientId)).thenReturn(Mono.just(true));
        when(productRepository.findById(productId)).thenReturn(Mono.just(product));
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(Mono.just(Order.builder().id("abc123").build()));
        when(outbox.registerEvent(Mockito.any())).thenReturn(Mono.empty());

        StepVerifier.create(orderService.createOrder(request))
            .expectNextMatches(order -> order.getId().equals("abc123"))
            .verifyComplete();
    }

    @Test
    void getOrderById_returnsOrder_whenOrderExists() {
        String orderId = "abc123";
        Order order = Order.builder()
            .id(orderId)
            .customerId(1L)
            .total(BigDecimal.valueOf(10))
            .build();

        when(orderRepository.findById(orderId)).thenReturn(Mono.just(order));

        StepVerifier.create(orderService.getOrderById(orderId))
            .expectNext(order)
            .verifyComplete();
    }
}
