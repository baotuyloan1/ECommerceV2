package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.order.Order;
import com.bnd.ecommerce.entity.order.OrderDetail;
import com.bnd.ecommerce.entity.order.OrderProductPK;
import com.bnd.ecommerce.repository.OrderDetailRepository;
import com.bnd.ecommerce.repository.OrderRepository;
import com.bnd.ecommerce.service.OrderService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  public OrderServiceImpl(
      OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
  }

  @Override
  public Iterable<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }

  @Override
  @Transactional
  public Order create(Order order) {
    List<OrderDetail> orderDetails = new ArrayList<>();

    setCreateUpdateTime(order);

    Order savedOrder = orderRepository.save(order);
    for (OrderDetail orderDetail : order.getOrderDetailList()) {
      OrderDetail orderDetail1 = new OrderDetail();
      orderDetail1.setPk(new OrderProductPK(savedOrder, orderDetail.getProduct()));
      orderDetail1.setQuantity(orderDetail.getQuantity());
      orderDetails.add(orderDetail1);
    }
    orderDetailRepository.saveAll(orderDetails);
    return savedOrder;
  }

  private static void setCreateUpdateTime(Order order) {
    order.getCustomer().setCreateTime(new Timestamp(new Date().getTime()));
    order.getCustomer().setUpdateTime(new Timestamp(new Date().getTime()));
    order.setCreateTime(new Timestamp(new Date().getTime()));
    order.setUpdateTime(new Timestamp(new Date().getTime()));
  }

  @Override
  public void update(Order order) {
    this.orderRepository.save(order);
  }
}
