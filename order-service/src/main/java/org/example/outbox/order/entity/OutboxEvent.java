package org.example.outbox.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "outbox_events")
@Entity
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payload")
    private String payload;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}
