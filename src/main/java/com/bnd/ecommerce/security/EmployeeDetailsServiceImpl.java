package com.bnd.ecommerce.security;

import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.service.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetailsServiceImpl implements UserDetailsService {

  private final EmployeeService employeeService;

  public EmployeeDetailsServiceImpl(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Employee employee = employeeService.findByEmail(username);
    if (employee == null) {
      throw new UsernameNotFoundException("Could not find user");
    }
    return new EmployeeDetails(employee);
  }
}
