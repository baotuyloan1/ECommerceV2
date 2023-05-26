package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.repository.CustomerRepository;
import com.bnd.ecommerce.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }
}
