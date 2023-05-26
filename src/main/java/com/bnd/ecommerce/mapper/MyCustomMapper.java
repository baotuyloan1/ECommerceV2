//package com.bnd.ecommerce.mapper;
//
//import com.bnd.ecommerce.dto.EmployeeCreateDto;
//import com.bnd.ecommerce.dto.EmployeeUpdateDto;
//import com.bnd.ecommerce.entity.employee.Employee;
//import com.bnd.ecommerce.service.RoleService;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyCustomMapper implements MapStructMapper {
//  private final RoleService roleService;
//
//  public MyCustomMapper(RoleService roleService) {
//    this.roleService = roleService;
//  }
//
//  @Override
//  public EmployeeUpdateDto employeeToEmployeePostDto(Employee employee) {
//    if (employee == null) {
//      return null;
//    }
//
//    EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
//
//    employeeUpdateDto.setCreateTime(employee.getCreateTime());
//    employeeUpdateDto.setUpdateTime(employee.getUpdateTime());
//    employeeUpdateDto.setEnabled(employee.isEnabled());
//    employeeUpdateDto.setId(employee.getId());
//    employeeUpdateDto.setFirstName(employee.getFirstName());
//    employeeUpdateDto.setLastName(employee.getLastName());
//    employeeUpdateDto.setEmail(employee.getEmail());
//    employeeUpdateDto.setPassword(employee.getPassword());
//    employeeUpdateDto.setPhone(employee.getPhone());
//    employeeUpdateDto.setRoles(employee.getRoles());
//    //    Set<Role> roles = new HashSet<>();
//    //    for (EmployeeRole employeeRole : employee.getEmployeeRoles()) {
//    //      roles.add(employeeRole.getRole());
//    //    }
//    //    employeePostDto.setRoles(roles);
//    return employeeUpdateDto;
//  }
//
//  @Override
//  public Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto) {
//    if (employeeUpdateDto == null) {
//      return null;
//    }
//
//    Employee employee = new Employee();
//
//    employee.setCreateTime(employeeUpdateDto.getCreateTime());
//    employee.setUpdateTime(employeeUpdateDto.getUpdateTime());
//    employee.setEnabled(employeeUpdateDto.isEnabled());
//    employee.setPhone(employeeUpdateDto.getPhone());
//    employee.setId(employeeUpdateDto.getId());
//    employee.setEmail(employeeUpdateDto.getEmail());
//    employee.setPassword(employeeUpdateDto.getPassword());
//    employee.setFirstName(employeeUpdateDto.getFirstName());
//    employee.setLastName(employeeUpdateDto.getLastName());
//    //    employeePostDto
//    //        .getRoles()
//    //        .forEach(
//    //            role -> {
//    //              EmployeeRole employeeRole = new EmployeeRole();
//    //              employeeRole.setRole(role);
//    //              employeeRole.setEmployee(employee);
//    //              employee.addEmployeeRole(employeeRole);
//    //            });
//    employee.setRoles(employeeUpdateDto.getRoles());
//    return employee;
//  }
//
//  @Override
//  public Employee employeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto) {
//    return null;
//  }
//}
