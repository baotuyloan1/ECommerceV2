package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.CustomerRepository;
import com.bnd.ecommerce.service.CustomerService;
import com.bnd.ecommerce.service.RoleService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final MapStructMapper mapStructMapper;

  private final RoleService roleService;

  private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  public CustomerServiceImpl(
      CustomerRepository customerRepository,
      MapStructMapper mapStructMapper,
      RoleService roleService) {
    this.customerRepository = customerRepository;
    this.mapStructMapper = mapStructMapper;
    this.roleService = roleService;
  }

  @Override
  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public CustomerDto findById(long id) {
    Customer foundCustomer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    return mapStructMapper.customerToCustomerDto(foundCustomer);
  }

  @Override
  public boolean isValidPassword(CustomerDto customerDto) {
    Customer customer =
        customerRepository
            .findById(customerDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    return new BCryptPasswordEncoder()
        .encode(customerDto.getPassword())
        .equals(customer.getPassword());
  }

  @Override
  public CustomerDto handleLoginCustomer(CustomerDto customerDto) {
    Customer customer =
        customerRepository
            .findByUserName(customerDto.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

    if (bCryptPasswordEncoder.matches(customerDto.getPassword(), customer.getPassword())) {
      return mapStructMapper.customerToCustomerDto(customer);
    } else return null;
  }

  @Override
  public CustomerDto save(CustomerDto customerDto) {
    Customer customer = mapStructMapper.customerDtoToCustomer(customerDto);
    customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
    Set<Role> roleSet = new HashSet<>();
    Role role = roleService.findByRoleName("USER");
    roleSet.add(role);
    customer.setRoleSet(roleSet);
    return mapStructMapper.customerToCustomerDto(customerRepository.save(customer));
  }
}
