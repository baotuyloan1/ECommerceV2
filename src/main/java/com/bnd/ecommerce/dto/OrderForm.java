package com.bnd.ecommerce.dto;

import java.util.List;

public class OrderForm {

  private List<OrderProductDto> orderProductDtoList;

  public List<OrderProductDto> getOrderProductDtoList() {
    return orderProductDtoList;
  }

  public void setOrderProductDtoList(List<OrderProductDto> orderProductDtoList) {
    this.orderProductDtoList = orderProductDtoList;
  }
}
