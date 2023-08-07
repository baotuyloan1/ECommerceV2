package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.dto.api.OrderDto;
import com.bnd.ecommerce.dto.api.OrderDtoResponse;
import com.bnd.ecommerce.security.customer.CustomerDetails;
import com.bnd.ecommerce.service.OrderService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order", description = "Order APIs")
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<?> create(
      @Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    OrderDto savedOrderDto = orderService.create(orderDto);
    return ResponseEntity.created(URI.create("/api/orders/" + savedOrderDto.getId()))
        .body(savedOrderDto);
  }

  @GetMapping
  public ResponseEntity<?> getOrders() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getPrincipal() instanceof CustomerDetails customerDetails) {
      List<OrderDtoResponse> orderDtoResponseList =
          orderService.getOrderByCustomerId(customerDetails.getCustomerDto().getId());
      return ResponseEntity.ok(orderDtoResponseList);
    }

    return ResponseEntity.badRequest().body("Invalid token");
  }

  @PatchMapping("/cancelOrder/{id}")
  public ResponseEntity<?> cancelOrder(@PathVariable("id") Long idOrder) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getPrincipal() instanceof CustomerDetails customerDetails) {
      return ResponseEntity.ok(
          orderService.cancelOrder(idOrder, customerDetails.getCustomerDto().getId()));
    }
    return ResponseEntity.badRequest().body("Invalid token");
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getPrincipal() instanceof CustomerDetails customerDetails) {
      return ResponseEntity.ok(
          orderService.getOrderById(id, customerDetails.getCustomerDto().getId()));
    }
    return ResponseEntity.badRequest().body("Invalid token");
  }
}
