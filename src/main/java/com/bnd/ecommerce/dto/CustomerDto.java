package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

public class CustomerDto {

  private long id;

  private String firstName;

  private String lastName;

  //    @UniqueEmailCustomer
  private String email;

  private String phone;

  private String password;

  private Set<CustomerAddressDto> customerAddressDtoSet = new HashSet<>();



  private GenderEnum genderEnum;

  private String userName;

  @JsonIgnoreProperties("employees")
  private Set<Role> roleSet = new HashSet<>();



  public Set<Role> getRoleSet() {
    return roleSet;
  }

  public void setRoleSet(Set<Role> roleSet) {
    this.roleSet = roleSet;
  }

  public GenderEnum getGenderEnum() {
    return genderEnum;
  }

  public void setGenderEnum(GenderEnum genderEnum) {
    this.genderEnum = genderEnum;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<CustomerAddressDto> getCustomerAddressDtoSet() {
    return customerAddressDtoSet;
  }

  public void setCustomerAddressDtoSet(Set<CustomerAddressDto> customerAddressDtoSet) {
    this.customerAddressDtoSet = customerAddressDtoSet;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
