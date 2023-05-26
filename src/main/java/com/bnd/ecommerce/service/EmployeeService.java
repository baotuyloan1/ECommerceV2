package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.EmployeeCreateDto;
import com.bnd.ecommerce.dto.EmployeeUpdateDto;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.enums.LogTypeEmployee;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface EmployeeService {

  Employee findByEmail(String email);

  Employee save(
      Employee employee,
      Authentication authentication,
      String action,
      LogTypeEmployee logTypeEmployee);

  Page<Employee> listAll(int size, int numPage, String sortField, String sortDir, String keyword);

  Employee findById(Long id);

  Employee updateNoPassword(long id, EmployeeUpdateDto employeeUpdateDto, Authentication authentication);

  Employee createNewEmployee(EmployeeCreateDto employee, Authentication authentication);

  EmployeeUpdateDto getEmployeePostDtoById(long id);

  boolean deleteById(long id, Authentication authentication);

}
