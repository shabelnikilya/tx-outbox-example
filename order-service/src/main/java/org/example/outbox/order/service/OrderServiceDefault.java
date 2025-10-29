package org.example.outbox.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.outbox.order.dto.OrderDto;
import org.example.outbox.order.entity.OutboxEvent;
import org.example.outbox.order.mapper.EntityMapper;
import org.example.outbox.order.repository.OrderRepository;
import org.example.outbox.order.repository.OutboxEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceDefault implements OrderService {

    private final OrderRepository orderRepository;
    private final OutboxEventRepository outboxEventRepository;
    private final EntityMapper mapper;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public OrderDto save(OrderDto order) {
        var savedOrder = orderRepository.save(mapper.toEntity(order));
        String payload;
        try {
            payload = objectMapper.writeValueAsString(order);
            var outboxEntity = new OutboxEvent();
            outboxEntity.setEventType("CREATED");
            outboxEntity.setPayload(payload);
            outboxEventRepository.save(outboxEntity);
        } catch (JsonProcessingException e) {
            log.error("Error in process serialize ", e);
            throw new RuntimeException(e);
        }
        return mapper.toDto(savedOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDto getById(Long orderId) {
        var order = orderRepository.getReferenceById(orderId);
        return mapper.toDto(order);
    }

    @Transactional
    @Override
    public void delete(Long orderId) {
        var outboxEntity = new OutboxEvent();
        outboxEntity.setEventType("DELETED");
        outboxEntity.setPayload(String.valueOf(orderId));
        outboxEventRepository.save(outboxEntity);
        orderRepository.deleteById(orderId);
    }
}
