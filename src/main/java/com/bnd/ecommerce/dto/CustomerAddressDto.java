package com.bnd.ecommerce.dto;

public class CustomerAddressDto {

  private long id;

  private String fullName;
  private String phone;
  private String address;

  private CustomerDto customerDto;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public CustomerDto getCustomerDto() {
    return customerDto;
  }

  public void setCustomerDto(CustomerDto customerDto) {
    this.customerDto = customerDto;
  }
}
