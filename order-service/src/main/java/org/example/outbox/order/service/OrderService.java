package org.example.outbox.order.service;

import org.example.outbox.order.dto.OrderDto;

public interface OrderService {


    OrderDto save(OrderDto order);

    OrderDto getById(Long orderId);

    void delete(Long orderId);
}
