package com.bnd.ecommerce.entity.stock;

import com.bnd.ecommerce.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Embeddable
public class StockID implements Serializable {

  @NotBlank
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  private Product product;

  @NotBlank
  @ManyToOne
  @JoinColumn(name = "warehouse_id")
  private Warehouse warehouse;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Warehouse getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  public StockID() {}

  public StockID(Product product, Warehouse warehouse) {
    this.product = product;
    this.warehouse = warehouse;
  }

  @Override
  public String toString() {
    return "StockID{" + "product=" + product.getId() + ", warehouse=" + warehouse.getId() + '}';
  }
}
