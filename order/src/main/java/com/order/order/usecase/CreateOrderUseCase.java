package com.order.order.usecase;

import com.order.order.dto.request.OrderDtoRequest;
import com.order.order.dto.response.OrderDto;

public interface CreateOrderUseCase {
    public OrderDto createOrder(OrderDtoRequest orderDtoRequest);
}
