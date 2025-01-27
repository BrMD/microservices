package com.order.order.service;

import com.order.order.dto.mapper.OrderMapper;
import com.order.order.dto.request.OrderDtoRequest;
import com.order.order.dto.response.OrderDto;
import com.order.order.repositories.OrderRepository;
import com.order.order.usecase.CreateOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderImpl implements CreateOrderUseCase {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public CreateOrderImpl(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDtoRequest orderDtoRequest){
        var order = orderMapper.toEntity(orderDtoRequest);
        order.setOrderValue(1.1);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
