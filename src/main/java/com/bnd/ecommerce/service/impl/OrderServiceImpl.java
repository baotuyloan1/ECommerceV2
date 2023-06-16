package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.api.OrderDetailDto;
import com.bnd.ecommerce.dto.api.OrderDetailDtoReponse;
import com.bnd.ecommerce.dto.api.OrderDto;
import com.bnd.ecommerce.dto.api.OrderDtoResponse;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.entity.customer.CustomerAddress;
import com.bnd.ecommerce.entity.order.Order;
import com.bnd.ecommerce.entity.order.OrderDetail;
import com.bnd.ecommerce.entity.order.OrderProductPK;
import com.bnd.ecommerce.enums.OrderStatus;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.CustomerAddressRepository;
import com.bnd.ecommerce.repository.CustomerRepository;
import com.bnd.ecommerce.repository.OrderDetailRepository;
import com.bnd.ecommerce.repository.OrderRepository;
import com.bnd.ecommerce.service.OrderService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderDetailRepository orderDetailRepository;

  private final CustomerAddressRepository customerAddressRepository;

  private final CustomerRepository customerRepository;

  private final MapStructMapper mapStructMapper;

  public OrderServiceImpl(
      OrderRepository orderRepository,
      OrderDetailRepository orderDetailRepository,
      CustomerAddressRepository customerAddressRepository,
      CustomerRepository customerRepository,
      MapStructMapper mapStructMapper) {
    this.orderRepository = orderRepository;
    this.orderDetailRepository = orderDetailRepository;
    this.customerAddressRepository = customerAddressRepository;
    this.customerRepository = customerRepository;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public Iterable<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }

  @Override
  @Transactional
  public OrderDto create(OrderDto orderDto) {
    List<OrderDetail> orderDetails = new ArrayList<>();

    Order order = new Order();
    setCreateUpdateTime(order);
    order.setStatus(OrderStatus.IN_PROGRESS);
    order.setTotalPrice(orderDto.getTotalPrice());
    CustomerAddress customerAddress =
        customerAddressRepository
            .findById(orderDto.getCustomerAdress())
            .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
    order.setCustomerAddress(customerAddress);
    Order savedOrder = orderRepository.save(order);

    for (OrderDetailDto orderDetailDto : orderDto.getProducts()) {
      OrderDetail orderDetail = new OrderDetail();
      Product product = new Product();
      product.setId(orderDetailDto.getProductId());
      orderDetail.setPk(new OrderProductPK(savedOrder, product));
      orderDetail.setQuantity(orderDetailDto.getQuantity());
      orderDetails.add(orderDetail);
    }
    orderDetailRepository.saveAll(orderDetails);
    orderDto.setId(savedOrder.getId());
    return orderDto;
  }

  private static void setCreateUpdateTime(Order order) {
    //    order.getCustomer().setCreateTime(new Timestamp(new Date().getTime()));
    //    order.getCustomer().setUpdateTime(new Timestamp(new Date().getTime()));
    order.setCreateTime(new Timestamp(new Date().getTime()));
    order.setUpdateTime(new Timestamp(new Date().getTime()));
  }

  @Override
  public void update(Order order) {
    this.orderRepository.save(order);
  }

  @Override
  public List<OrderDtoResponse> getOrderByCustomerId(long id) {
    List<OrderDtoResponse> orderDtoList = new ArrayList<>();
    List<Order> orderList = orderRepository.getOrdersByCusId(id);
    for (Order order : orderList) {
      OrderDtoResponse orderDtoResponse = new OrderDtoResponse();
      CustomerAddress customerAddress = order.getCustomerAddress();
      List<OrderDetail> orderDetailList = order.getOrderDetailList();
      orderDtoResponse.setCustomerAddress(customerAddress);
      for (OrderDetail orderDetail : orderDetailList) {

        orderDetail.setPk(
            new OrderProductPK(order, (Product) Hibernate.unproxy(orderDetail.getProduct())));
        OrderDetailDtoReponse orderDetailDtoReponse = new OrderDetailDtoReponse();
        orderDetailDtoReponse.setProduct(orderDetail.getProduct());
        orderDetailDtoReponse.setCreateTime(orderDetail.getCreateTime());
        orderDetailDtoReponse.setQuantity(orderDetail.getQuantity());
        orderDtoResponse.addOrderDetail(orderDetailDtoReponse);
      }

      orderDtoResponse.setStatusOrder(OrderStatus.IN_PROGRESS);
      orderDtoList.add(orderDtoResponse);
    }
    return orderDtoList;
  }

  @Transactional
  @Override
  public boolean cancelOrder(Long idOrder, Long customerId) {
  Order order = orderRepository.getOrderByIdAndCustomerId(idOrder, customerId).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
  order.setStatus(OrderStatus.CANCELED);
    return true;
  }

  @Override
  public Order getOrderById(Long id, Long customerId) {
    Order order = orderRepository
            .getOrderByIdAndCustomerId(id, customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    for (OrderDetail orderDetail : order.getOrderDetailList()) {
        orderDetail.getPk().setProduct((Product) Hibernate.unproxy(orderDetail.getProduct()));
    }
    return order;
  }
}
