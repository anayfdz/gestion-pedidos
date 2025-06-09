package com.example.gestion_pedidos.outbox.repository;

import com.example.gestion_pedidos.outbox.OutboxEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxEventRepository extends ReactiveMongoRepository<OutboxEvent, String> {
    
}
