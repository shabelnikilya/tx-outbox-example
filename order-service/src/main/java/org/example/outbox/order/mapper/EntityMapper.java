package org.example.outbox.order.mapper;

import org.example.outbox.order.dto.OrderDto;
import org.example.outbox.order.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {



    OrderEntity toEntity(OrderDto dto);


    OrderDto toDto(OrderEntity entity);
}
