package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.customer.Customer;

public interface CustomerService {
    Customer save(Customer customer);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto findById(long id);

    boolean isValidPassword(CustomerDto customerDto);

    CustomerDto handleLoginCustomer(CustomerDto customerDto);
}
