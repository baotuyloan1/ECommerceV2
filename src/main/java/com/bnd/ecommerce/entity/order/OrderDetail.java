package com.bnd.ecommerce.entity.order;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.bnd.ecommerce.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail extends CreateUpdateTimeStamp {

  @JsonIgnoreProperties(value = {"order"})
  @EmbeddedId
  private OrderProductPK pk;

  public OrderDetail() {}

  public OrderProductPK getPk() {
    return pk;
  }

  public void setPk(OrderProductPK pk) {
    this.pk = pk;
  }

  private int quantity;

  public OrderDetail(Order order, Product product, int quantity) {
    pk = new OrderProductPK();
    this.pk.setOrder(order);
    this.pk.setProduct(product);
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Transient
  public Product getProduct() {
    return this.pk.getProduct();
  }

  @Transient
  public Float getTotalPrice() {
    return this.pk.getProduct().getPrice() * quantity;
  }


}
