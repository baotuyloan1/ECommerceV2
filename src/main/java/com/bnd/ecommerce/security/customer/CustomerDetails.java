package com.bnd.ecommerce.security.customer;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.entity.employee.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerDetails implements UserDetails {

  private final CustomerDto customerDto;

  public CustomerDetails(CustomerDto customerDto) {
    this.customerDto = customerDto;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Role> roles = customerDto.getRoleSet();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return customerDto.getPassword();
  }

  @Override
  public String getUsername() {
    return customerDto.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public CustomerDto getCustomerDto(){
    return this.customerDto;
  }
}
