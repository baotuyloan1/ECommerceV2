package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.api.OrderDto;
import com.bnd.ecommerce.dto.api.OrderDtoResponse;
import com.bnd.ecommerce.entity.order.Order;

import java.util.List;

public interface OrderService {
    Iterable<Order> getAllOrders();

    OrderDto create(OrderDto order);

    void update(Order order);

    List<OrderDtoResponse> getOrderByCustomerId(long id);

    boolean cancelOrder(Long idOrder, Long customerId);

    Order getOrderById(Long id,  Long customerId);
}
