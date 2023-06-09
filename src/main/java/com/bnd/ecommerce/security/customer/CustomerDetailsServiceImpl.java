package com.bnd.ecommerce.security.customer;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.security.employee.EmployeeDetails;
import com.bnd.ecommerce.service.CustomerService;
import com.bnd.ecommerce.service.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsServiceImpl implements UserDetailsService {

  private final CustomerService customerService;

  public CustomerDetailsServiceImpl(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    CustomerDto customerDto = customerService.findById(Integer.parseInt(id));
    if (customerDto == null) {
      throw new UsernameNotFoundException("Could not find user");
    }
    return new CustomerDetails(customerDto);
  }
}
