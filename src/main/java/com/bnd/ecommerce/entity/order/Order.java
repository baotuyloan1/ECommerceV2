package com.bnd.ecommerce.entity.order;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.entity.customer.CustomerAddress;
import com.bnd.ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"order\"")
public class Order extends CreateUpdateTimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @NotNull
  @JoinColumn(name = "customer_address_id")
  @JsonIgnoreProperties("customer")
  private CustomerAddress customerAddress;

  @Column(name = "order_date")
  private Timestamp createTime;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "pk.order")
  @Valid
  @JsonProperty("products")
  private List<OrderDetail> orderDetailList = new ArrayList<>();

  private float totalPrice;

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CustomerAddress getCustomerAddress() {
    return customerAddress;
  }

  public void setCustomerAddress(CustomerAddress customerAddress) {
    this.customerAddress = customerAddress;
  }

  @Override
  public Timestamp getCreateTime() {
    return createTime;
  }

  public List<OrderDetail> getOrderDetailList() {
    return orderDetailList;
  }

  public void setOrderDetailList(List<OrderDetail> orderDetailList) {
    this.orderDetailList = orderDetailList;
  }

  @Override
  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  @Transient
  public float getTotalPrice() {
    float sum = 0f;
    for (OrderDetail orderDetail : getOrderDetailList()) {
      sum += orderDetail.getTotalPrice();
    }
    return sum;
  }

  @Transient
  public int getNumberOfProducts() {
    return this.orderDetailList.size();
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }


}
