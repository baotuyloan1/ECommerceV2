package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import javax.validation.constraints.Min;

public class StockDto extends CreateUpdateTimeStamp {

  private long productId;

  private int warehouseId;

  @Min(value = 1)
  private long quantityInStock;

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public int getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(int warehouseId) {
    this.warehouseId = warehouseId;
  }

  public long getQuantityInStock() {
    return quantityInStock;
  }

  public void setQuantityInStock(long quantityInStock) {
    this.quantityInStock = quantityInStock;
  }
}
