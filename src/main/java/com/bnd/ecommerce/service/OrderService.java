package com.bnd.ecommerce.service;

import com.bnd.ecommerce.entity.order.Order;

import java.util.List;

public interface OrderService {
    Iterable<Order> getAllOrders();

    Order create(Order order);

    void update(Order order);
}
