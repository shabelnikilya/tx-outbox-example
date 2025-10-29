package org.example.outbox.order.dto;

public record OrderDto(
        Long id,
        String product,
        String description,
        String customerName,
        String status
) {
}
