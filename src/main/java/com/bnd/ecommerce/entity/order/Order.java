package com.bnd.ecommerce.entity.order;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "order_date")
  private Timestamp createTime;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "pk.order")
  @Valid
  private List<OrderDetail> orderDetailList = new ArrayList<>();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
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
