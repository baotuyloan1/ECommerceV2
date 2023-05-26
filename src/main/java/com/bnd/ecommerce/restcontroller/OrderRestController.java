package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.dto.OrderProductDto;
import com.bnd.ecommerce.entity.order.Order;
import com.bnd.ecommerce.entity.order.OrderDetail;
import com.bnd.ecommerce.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody Order order, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    List<OrderDetail> orderDetailList = order.getOrderDetailList();
    List<OrderProductDto> orderProducts = new ArrayList<>();
    return ResponseEntity.ok(orderService.create(order));
  }
}
