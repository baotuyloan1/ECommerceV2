//package com.bnd.ecommerce.converters;
//
//import com.bnd.ecommerce.entity.Role;
//import com.bnd.ecommerce.service.RoleService;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleToEmployeeRoleConverter implements Converter<String, EmployeeRole> {
//
//  private final RoleService roleService;
//
//  public RoleToEmployeeRoleConverter(RoleService roleService) {
//    this.roleService = roleService;
//  }
//
//  @Override
//  public EmployeeRole convert(String id) {
//    Role role = roleService.findById(Integer.parseInt(id));
//    EmployeeRole employeeRole = new EmployeeRole();
//    employeeRole.setRole(role);
//    return employeeRole;
//  }
//}
