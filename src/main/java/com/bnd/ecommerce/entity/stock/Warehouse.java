package com.bnd.ecommerce.entity.stock;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse extends CreateUpdateTimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String warehouseName;

  private String address;

  private String phone;



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
