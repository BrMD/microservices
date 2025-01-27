package com.order.order.controller;

import com.order.order.dto.request.OrderDtoRequest;
import com.order.order.dto.response.OrderDto;
import com.order.order.service.CreateOrderImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final CreateOrderImpl createOrderImpl;

    public OrderController(CreateOrderImpl createOrderImpl) {
        this.createOrderImpl = createOrderImpl;
    }

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDtoRequest orderDtoRequest){
        OrderDto orderDto = createOrderImpl.createOrder(orderDtoRequest);
        return new ResponseEntity<>(orderDto,HttpStatusCode.valueOf(200));
    }
}
