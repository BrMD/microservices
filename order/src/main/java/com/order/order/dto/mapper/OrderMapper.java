package com.order.order.dto.mapper;

import com.order.order.dto.request.OrderDtoRequest;
import com.order.order.dto.response.OrderDto;
import com.order.order.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order){
        if(order == null) return null;
        return new OrderDto(order.getId(), order.getUserId(), order.getCartId(), order.getOrderValue());
    }

    public Order toEntity(OrderDto orderDto){
        if(orderDto == null) return null;
        return new Order(orderDto.getId(), orderDto.getUserId(), orderDto.getCartId(),1, orderDto.getValue());
    }

    public Order toEntity(OrderDtoRequest orderDtoRequest){
        if(orderDtoRequest == null) return null;
        return new Order(orderDtoRequest.getUserId(), orderDtoRequest.getCartId());
    }
}
