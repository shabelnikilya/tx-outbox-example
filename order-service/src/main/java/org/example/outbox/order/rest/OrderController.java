package org.example.outbox.order.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.outbox.order.dto.OrderDto;
import org.example.outbox.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto create(@RequestBody OrderDto req) {
        return orderService.save(req);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> get(@PathVariable Long orderId) {
        try {
            return ResponseEntity.ok(orderService.getById(orderId));
        } catch (EntityNotFoundException e) {
            log.info("User with id={} not found", orderId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.noContent()
                .build();
    }
}
