package com.example.gestion_pedidos.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.example.gestion_pedidos.outbox.repository.OutboxEventRepository;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionalOutbox {
      private final OutboxEventRepository outboxEventRepository;

    public Mono<Void> registerEvent(OutboxEvent event) {
        return outboxEventRepository.save(event)
            .then();
    }
}
