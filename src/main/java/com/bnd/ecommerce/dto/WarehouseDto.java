package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;

public class WarehouseDto extends CreateUpdateTimeStamp {

  private int id;

  private String warehouseName;

  private String address;

  private String phone;

  private StockDto stockDto;

  public StockDto getStockDto() {
    return stockDto;
  }

  public void setStockDto(StockDto stockDto) {
    this.stockDto = stockDto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
