package com.order.order.service;

import com.order.order.dto.mapper.OrderMapper;
import com.order.order.dto.request.OrderDtoRequest;
import com.order.order.dto.response.CartDto;
import com.order.order.dto.response.OrderDto;
import com.order.order.dto.response.ProductDtoResponse;
import com.order.order.entities.Order;
import com.order.order.repositories.OrderRepository;
import com.order.order.usecase.CreateOrderUseCase;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CreateOrderImpl implements CreateOrderUseCase {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public CreateOrderImpl(OrderMapper orderMapper, OrderRepository orderRepository, DiscoveryClient discoveryClient, RestClient restClient) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
    }

    @Override
    public OrderDto createOrder(OrderDtoRequest orderDtoRequest){
        ServiceInstance cartInstance = discoveryClient.getInstances("cart").getFirst();
        CartDto cartDto =
                restClient.get().uri(cartInstance.getUri() + "/" + orderDtoRequest.getUserId()).retrieve().body(CartDto.class);
        Order newOrder = new Order(cartDto.getUserId(), cartDto.getId());
        newOrder.setStatus(1);
    }

    public Float getTotal(List<ProductDtoResponse> listProducts){
        return listProducts.stream().reduce(0, (subtotal, element) -> quantxProd(subtotal) + quantxProd(element));
    }

    public Float quantxProd(ProductDtoResponse productDtoResponse){
        ServiceInstance productInstance = discoveryClient.getInstances("products").getFirst();
        Float price =
                restClient.get().uri(productInstance.getUri() + "/price/" + productDtoResponse
                        .getId()).retrieve().body(Float.class);
        return price * productDtoResponse.getQuantity();
    }
}
