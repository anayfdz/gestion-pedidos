package com.example.gestion_pedidos.outbox;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "outbox_events")
public class OutboxEvent {
    @Id
    private String id;

    private String type;

    private String aggregateId;

    private Object payload;
    
    private Instant createdAt;

     public OutboxEvent(String type, String aggregateId, Object payload) {
        this.type = type;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.createdAt = Instant.now();
    }
    
}
