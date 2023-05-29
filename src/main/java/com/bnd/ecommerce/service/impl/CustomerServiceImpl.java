package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.CustomerRepository;
import com.bnd.ecommerce.service.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final MapStructMapper mapStructMapper;

  public CustomerServiceImpl(
      CustomerRepository customerRepository, MapStructMapper mapStructMapper) {
    this.customerRepository = customerRepository;
    this.mapStructMapper = mapStructMapper;
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
  public CustomerDto save(CustomerDto customerDto) {
    Customer customer = mapStructMapper.customerDtoToCustomer(customerDto);
    customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
    return mapStructMapper.customerToCustomerDto(customerRepository.save(customer));
  }
}
