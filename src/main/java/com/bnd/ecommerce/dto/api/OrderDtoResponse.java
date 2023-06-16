package com.bnd.ecommerce.dto.api;

import com.bnd.ecommerce.entity.customer.CustomerAddress;
import com.bnd.ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;


public class OrderDtoResponse {



    @JsonIgnoreProperties({"customer"})
    private CustomerAddress customerAddress;


    private OrderStatus statusOrder;


    public OrderStatus getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(OrderStatus statusOrder) {
        this.statusOrder = statusOrder;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<OrderDetailDtoReponse> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetailDtoReponse> orders) {
        this.orders = orders;
    }

    private List<OrderDetailDtoReponse> orders;


    @Transient
    public void addOrderDetail(OrderDetailDtoReponse orderDetail){
        if (this.orders == null) this.orders = new ArrayList<>();
        this.orders.add(orderDetail);
    }



}
